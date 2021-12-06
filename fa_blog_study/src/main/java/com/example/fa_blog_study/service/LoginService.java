package com.example.fa_blog_study.service;

import com.example.fa_blog_study.dao.pojo.SysUser;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.param.LoginParams;

public interface LoginService {
    Result login(LoginParams loginParams);
    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParams loginParams);
}
