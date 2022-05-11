package com.jurenzhao.mall.service;

import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.model.pojo.User;

/**
 * @autor :JuRenZhao
 * @Date :Created in 17:47 2022/5/3
 * @Description :用户service
 */
public interface UserService {
    /**
     * 查找用户
     * @param id 用户编号
     * @return
     */
    public User getUserById(Integer id);


    /**
     * 注册用户
     * @param userName
     * @param password
     * @throws MallException
     */
    void register(String userName, String password) throws MallException;

    User login(String userName, String password) throws MallException;

    void upldateInformation(User user) throws MallException;

    boolean checkAdminRole(User user);
}
