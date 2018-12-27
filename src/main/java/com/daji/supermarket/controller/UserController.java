package com.daji.supermarket.controller;

import com.daji.supermarket.entity.User;
import com.daji.supermarket.service.UserService;
import com.daji.supermarket.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author 大稽
 * @date2018/12/1214:09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserList")
    public Page<User> getUserList(Integer page){
        return userService.getUserList(page);
    }

    @PostMapping("/updateUser")
    public ResponseVo saveUser(User user) {
        User save = userService.updateUser(user);
        if(save!=null) {
            return new ResponseVo(200, save, "添加成功");
        }else{
            return new ResponseVo(403, null, "添加失败");
        }
    }


}
