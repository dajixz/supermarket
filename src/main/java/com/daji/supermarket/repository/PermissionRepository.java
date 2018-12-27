package com.daji.supermarket.repository;

import com.daji.supermarket.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 大稽
 * @date2018/12/1216:31
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
