package com.jurenzhao.mall.controller;

import com.jurenzhao.mall.common.ApiRestResponse;
import com.jurenzhao.mall.common.Constant;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.exception.MallExceptionEnum;
import com.jurenzhao.mall.model.pojo.User;
import com.jurenzhao.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @autor :JuRenZhao
 * @Date :Created in 17:45 2022/5/3
 * @Description :用户控制器
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("userName") String userName,
                                    @RequestParam("password") String password) throws MallException {


        if(StringUtils.isEmpty(userName)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if(StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if(password.length() < 8){
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_SHORT);
        }
        userService.register(userName,password);
        return ApiRestResponse.success();
    }
    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password, HttpSession session) throws MallException {
        if(StringUtils.isEmpty(userName)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if(StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if(password.length() < 8){
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_SHORT);
        }
        User user = userService.login(userName, password);
        user.setPassword(null);
        session.setAttribute(Constant.CURRENT_LOGIN_USER,user);
        return ApiRestResponse.success(user);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse updateUser(@RequestParam("signature") String signature,HttpSession session) throws MallException {
        User currentUser = (User) session.getAttribute(Constant.CURRENT_LOGIN_USER);
        if(currentUser == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        else {
            User  user = new User();
            user.setId(currentUser.getId());
            user.setPersonalizedSignature(signature);
            userService.upldateInformation(user);
            return ApiRestResponse.success();
        }
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session){
        session.removeAttribute(Constant.CURRENT_LOGIN_USER);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/login")
    @ResponseBody
    public ApiRestResponse adminLogin(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password, HttpSession session) throws MallException {
        if(StringUtils.isEmpty(userName)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if(StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if(password.length() < 8){
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_SHORT);
        }
        User user = userService.login(userName, password);
        if(userService.checkAdminRole(user)){
            user.setPassword(null);
            session.setAttribute(Constant.CURRENT_LOGIN_USER,user);
            return ApiRestResponse.success(user);
        }
        else {
          return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }

    }
}
