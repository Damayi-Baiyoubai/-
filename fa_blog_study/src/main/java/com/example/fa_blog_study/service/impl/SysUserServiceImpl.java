package com.example.fa_blog_study.service.impl;

import com.example.fa_blog_study.dao.SysUserMapper;
import com.example.fa_blog_study.dao.pojo.SysUser;
import com.example.fa_blog_study.service.LoginService;
import com.example.fa_blog_study.service.SysUserService;
import com.example.fa_blog_study.vo.ErrorCode;
import com.example.fa_blog_study.vo.LoginUserVo;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    LoginService loginService;

    @Override
    public SysUser getUserById(Long id) {
        SysUser userById = sysUserMapper.getUserById(id);
        if (userById == null) {
            userById = new SysUser();
            userById.setNickname("fa");
        }
        return userById;
    }

    @Override
    public UserVo getUserVoById(Long id) {
        SysUser userById = sysUserMapper.getUserById(id);
        if (userById == null) {
            userById = new SysUser();
            userById.setId(1L);
            userById.setAvatar("/static/img/logo.b3a48c0.png");
            userById.setNickname("码神之路");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userById,userVo);
        userVo.setId(String.valueOf(userById.getId()));
        return userVo;
    }

    @Override
    public SysUser findUser(String account, String password) {
        return sysUserMapper.findUser(account, password);
    }

    @Override
    public Result findUserByToken(String token) {
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null)
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(sysUser,loginUserVo);
        loginUserVo.setId(String.valueOf(sysUser.getId()));

        return Result.success(loginUserVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        return sysUserMapper.findUserByAccount(account);
    }

    @Override
    public int save(SysUser sysUser) {
        return sysUserMapper.save(sysUser);
    }


}
