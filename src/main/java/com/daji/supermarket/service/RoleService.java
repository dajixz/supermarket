package com.daji.supermarket.service;

import com.daji.supermarket.entity.Role;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1216:01
 */
public interface RoleService {
    List<Role> getRoleList();
    Role getRoleByRoleId(Integer roleId);
    Role saveRole(Role role);
}
