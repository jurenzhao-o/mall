package com.jurenzhao.mall.utils;


import com.jurenzhao.mall.common.Constant;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @autor :JuRenZhao
 * @Date :Created in 14:03 2022/4/30
 * @Description :MD5加密工具类
 */
public class MD5Utils {
    /**
     * 加密
     * @param source
     * @return
     */
    public static String md5Digest(String source){
        return DigestUtils.md5Hex(source+Constant.SALT);
    }

    public void ceshi(){
        System.out.println("xxxxx");
    }
}
