package com.jurenzhao.mall.service.impl;

import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.exception.MallExceptionEnum;
import com.jurenzhao.mall.model.dao.UserMapper;
import com.jurenzhao.mall.model.pojo.User;
import com.jurenzhao.mall.service.UserService;
import com.jurenzhao.mall.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @autor :JuRenZhao
 * @Date :Created in 17:49 2022/5/3
 * @Description :用户 实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    /**
     * 查找用户
     *
     * @param id 用户编号
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public void register(String userName, String password) throws MallException {
        User user = userMapper.selectByUsername(userName);
        if(user != null){
            throw new MallException(MallExceptionEnum.EXIT_USER);
        }
        user = new User();
        user.setUsername(userName);
        user.setPassword(MD5Utils.md5Digest(password));
        int i = userMapper.insertSelective(user);
        if(i == 0){
            throw new MallException(MallExceptionEnum.INSERT_USER_FAIL);
        }
    }

    @Override
    public User login(String userName, String password) throws MallException {
        password = MD5Utils.md5Digest(password);
        User user = userMapper.seletLogin(userName, password);
        if(user == null){
            throw new MallException(MallExceptionEnum.PASSWORD_ERROR);
        }else {
            return user;
        }
    }

    @Override
    public void upldateInformation(User user) throws MallException {
        int count = userMapper.updateByPrimaryKeySelective(user);
        if(count > 1){
            throw new MallException(MallExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public boolean checkAdminRole(User user){
        //1 是普通用户 2 是管理员
        return user.getRole().equals(2);
    }
}
