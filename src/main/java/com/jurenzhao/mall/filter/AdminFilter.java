package com.jurenzhao.mall.filter;

import com.jurenzhao.mall.common.Constant;
import com.jurenzhao.mall.model.pojo.User;
import com.jurenzhao.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @autor :JuRenZhao
 * @Date :Created in 21:48 2022/5/6
 * @Description :管理员校验过滤器
 */
public class AdminFilter implements Filter {
    @Autowired
    UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null){
            PrintWriter writer = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            writer.write("{\n" +
                    "    \"code\": 10007,\n" +
                    "    \"msg\": \"NEED_LOGIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            writer.flush();
            writer.close();
        }
        else {
            if(userService.checkAdminRole(user)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                PrintWriter writer = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
                writer.write("{\n" +
                        "    \"code\": 10009,\n" +
                        "    \"msg\": \"NEED_ADMIN\",\n" +
                        "    \"data\": null\n" +
                        "}");
                writer.flush();
                writer.close();
            }
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
