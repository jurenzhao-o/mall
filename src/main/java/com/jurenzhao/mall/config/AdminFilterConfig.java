package com.jurenzhao.mall.config;

import com.jurenzhao.mall.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @autor :JuRenZhao
 * @Date :Created in 22:02 2022/5/6
 * @Description :管理员身份过滤
 */
@Configuration
public class AdminFilterConfig {

    @Bean
    public AdminFilter adminFilter(){
        return  new AdminFilter();
    }

    @Bean(name = "adminfilterconf")
    public FilterRegistrationBean adminFilterConfig(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(adminFilter());
        filterRegistrationBean.addUrlPatterns("/admin/category/*");
        filterRegistrationBean.addUrlPatterns("/admin/product/*");
        filterRegistrationBean.setName("adminfilterconf");
        return filterRegistrationBean;
    }
}
