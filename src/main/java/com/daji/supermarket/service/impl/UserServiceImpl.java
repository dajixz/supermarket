package com.daji.supermarket.service.impl;

import com.daji.supermarket.entity.User;
import com.daji.supermarket.repository.UserRepository;
import com.daji.supermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author 大稽
 * @date2018/12/1214:05
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode("123456"));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userByUserId = userRepository.findUserByUserId(user.getUserId());
        if(user.getPassword()!=null){
            userByUserId.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getRoleList()!=null){
            userByUserId.setRoleList(user.getRoleList());
        }
        return userRepository.save(userByUserId);
    }

    @Override
    public Page<User> getUserList(Integer page) {
        Pageable pageable =  PageRequest.of(page-1, 5);
        return  userRepository.findAllByFlag(pageable,true);
    }

    @Override
    @Transactional
    public Integer deleteUserByUserId(String userId) {
        return userRepository.deleteUserByUserId(userId);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.getOne(userId);
    }
}
