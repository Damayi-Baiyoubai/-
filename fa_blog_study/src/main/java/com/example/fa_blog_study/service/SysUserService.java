package com.example.fa_blog_study.service;

import com.example.fa_blog_study.dao.pojo.SysUser;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.UserVo;

public interface SysUserService {
    SysUser getUserById(Long id);

    UserVo getUserVoById(Long id);
    SysUser findUser(String account, String password);

    Result findUserByToken(String token);

    SysUser findUserByAccount(String account);

    int save(SysUser sysUser);
}
