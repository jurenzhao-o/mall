package com.jurenzhao.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @autor :JuRenZhao
 * @Date :Created in 9:29 2022/5/4
 * @Description :常量配置
 */
@Configuration
public class Constant {

    //盐值
    public static final String SALT = "4RFTGYG100.@";

    //当前用户session的key
    public static final String  CURRENT_LOGIN_USER = "current_login_user";


    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc","price ase");
    }
}
