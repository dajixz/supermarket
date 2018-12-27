package com.daji.supermarket.controller;

import com.daji.supermarket.entity.Goods;
import com.daji.supermarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 大稽
 * @date2018/12/1011:09
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/login")
    public String toLoginView() {
        return "login";
    }

    @GetMapping("/goods-list")
    public String goodsListView() {
        return "goods-list";
    }

    @GetMapping("/user-list")
    public String userListView() {
        return "user-list";
    }

    @GetMapping("/goods-add")
    public String goodsAddView() {
        return "goods-add";
    }

    @GetMapping("/role-list")
    public String roleView() {
        return "role-list";
    }

    @GetMapping("/permission")
    public String permissionView() {
        return "permission";
    }

    @GetMapping("/welcome")
    public String welcomeView() {
        return "welcome";
    }

    @GetMapping("/user-add")
    public String userAddView() {
        return "user-add";
    }

    @GetMapping("/getGoods/{id}")
    public String getGoodsById(@PathVariable("id") Integer id, Model model) {
        Goods goodsById = goodsService.getGoodsById(id);
        model.addAttribute("goods", goodsById);
        return "goods-update";
    }

    @GetMapping("/user-info")
    public String toUserInfoView(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", user);
        return "user-info";
    }
}
