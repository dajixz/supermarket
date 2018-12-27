package com.daji.supermarket.hander;

import com.daji.supermarket.vo.ResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 大稽
 * @date2018/12/1223:32
 */
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.info("登录失败！");
        response.setContentType("application/json;charset=UTF-8");
        ResponseVo vo = new ResponseVo();
        vo.setCode(500);
        vo.setMsg("用户名或密码错误！");
        response.getWriter().write(objectMapper.writeValueAsString(vo));

    }
}
