package com.daji.supermarket.service;

import com.daji.supermarket.entity.User;
import org.springframework.data.domain.Page;

/**
 * @author 大稽
 * @date2018/12/1214:04
 */
public interface UserService {
    User saveUser(User user);
    Page<User> getUserList(Integer page);
    Integer deleteUserByUserId(String userId);
    User getUserByUserId(String userId);
    User updateUser(User user);
}
