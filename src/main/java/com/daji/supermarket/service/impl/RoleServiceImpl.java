package com.daji.supermarket.service.impl;

import com.daji.supermarket.entity.Role;
import com.daji.supermarket.repository.RoleRepository;
import com.daji.supermarket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1216:03
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByRoleId(Integer roleId) {
        return roleRepository.getOne(roleId);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
