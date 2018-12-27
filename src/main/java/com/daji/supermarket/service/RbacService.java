package com.daji.supermarket.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 大稽
 * @date2018/10/1519:24
 */
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
