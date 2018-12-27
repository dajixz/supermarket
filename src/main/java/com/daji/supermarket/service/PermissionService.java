package com.daji.supermarket.service;

import com.daji.supermarket.entity.Permission;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1216:31
 */
public interface PermissionService {
    List<Permission> getPermissionList();
    Permission getPermissionByPermissionId(Integer permissionId);
}
