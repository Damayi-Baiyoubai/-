package com.example.fa_blog_study.handler;

import com.alibaba.fastjson.JSON;
import com.example.fa_blog_study.dao.pojo.SysUser;
import com.example.fa_blog_study.service.LoginService;
import com.example.fa_blog_study.vo.ErrorCode;
import com.example.fa_blog_study.vo.Result;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod))
            return true;
        String token = request.getHeader("Authorization");
        if (StringUtil.isNullOrEmpty(token)){
            String result = JSON.toJSONString(Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg()));
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(result);
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            String result = JSON.toJSONString(Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg()));
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(result);
            return false;
        }
        return true;
    }
}
