package com.daji.supermarket.service.impl;

import com.daji.supermarket.entity.Permission;
import com.daji.supermarket.repository.PermissionRepository;
import com.daji.supermarket.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1216:32
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getPermissionList() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionByPermissionId(Integer permissionId) {
        return permissionRepository.getOne(permissionId);
    }
}
