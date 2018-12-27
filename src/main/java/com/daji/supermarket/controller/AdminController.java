package com.daji.supermarket.controller;

import com.daji.supermarket.entity.Goods;
import com.daji.supermarket.entity.Permission;
import com.daji.supermarket.entity.Role;
import com.daji.supermarket.entity.User;
import com.daji.supermarket.service.GoodsService;
import com.daji.supermarket.service.PermissionService;
import com.daji.supermarket.service.RoleService;
import com.daji.supermarket.service.UserService;
import com.daji.supermarket.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1216:00
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @GetMapping("/getRoleList")
    @ResponseBody
    public ResponseVo getRoleList() {
        ResponseVo responseVo = new ResponseVo();
        List<Role> roleList = roleService.getRoleList();
        responseVo.setCode(200);
        responseVo.setData(roleList);
        return responseVo;
    }

    @GetMapping("/getPermissionList")
    @ResponseBody
    public ResponseVo getPermissionList() {
        ResponseVo responseVo = new ResponseVo();
        List<Permission> permissionList = permissionService.getPermissionList();
        responseVo.setCode(200);
        responseVo.setData(permissionList);
        return responseVo;
    }

    @DeleteMapping("/delUser")
    @ResponseBody
    public ResponseVo deleteUser(String userId) {
        Integer integer = userService.deleteUserByUserId(userId);
        if (integer == 1) {
            return new ResponseVo(200, null, "删除成功");
        } else {
            return new ResponseVo(403, null, "删除失败");
        }
    }

    @GetMapping("/user-edit/{userId}")
    public String toUserEditView(@PathVariable("userId") String userId, Model model) {
        User user = userService.getUserByUserId(userId);
        List<Role> roleList = user.getRoleList();
        List<Integer> flagList = new ArrayList<>();
        for (Role role : roleList) {
            int roleId = role.getRoleId();
            flagList.add(roleId);
        }
        List<Role> roles = roleService.getRoleList();
        for (Role role : roles) {
            int roleId = role.getRoleId();
            if (flagList.contains(roleId)) {
                role.setFlag(true);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "/user-edit";
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public ResponseVo updateUser(User user) {
        ResponseVo responseVo = new ResponseVo();
        List<Integer> roles = user.getRoles();
        List<Role> roleList = new ArrayList<>();
        if (roles != null) {
            for (Integer roleId : roles) {
                Role roleByRoleId = roleService.getRoleByRoleId(roleId);
                roleList.add(roleByRoleId);
            }
        }
        user.setRoleList(roleList);
        User user1 = userService.updateUser(user);
        if (user1 != null) {
            responseVo.setCode(200);
            responseVo.setMsg("修改成功");
        } else {
            responseVo.setCode(403);
            responseVo.setMsg("修改失败");
        }
        return responseVo;
    }

    @GetMapping("/role-edit/{roleId}")
    public String toRoleEditView(@PathVariable("roleId") Integer roleId, Model model) {
        Role role = roleService.getRoleByRoleId(roleId);
        List<Permission> permissionList = role.getPermissionList();
        List<Integer> flagList = new ArrayList<>();
        for (Permission permission : permissionList) {
            int permissionId = permission.getPermissionId();
            flagList.add(permissionId);
        }
        List<Permission> permissions = permissionService.getPermissionList();
        for (Permission permission : permissions) {
            int permissionId = permission.getPermissionId();
            if (flagList.contains(permissionId)) {
                permission.setFlag(true);
            }
        }
        model.addAttribute("role", role);
        model.addAttribute("permissions", permissions);
        return "role-edit";
    }

    @PutMapping("/updateRole")
    @ResponseBody
    public ResponseVo updateRole(Role role) {
        ResponseVo responseVo = new ResponseVo();
        List<Integer> permissions = role.getPermissions();
        System.out.println(permissions);
        List<Permission> permissionList = new ArrayList<>();
        if (permissions != null) {
            for (Integer permissionId : permissions) {
                if (permissionId != null) {
                    Permission permission = permissionService.getPermissionByPermissionId(permissionId);
                    permissionList.add(permission);
                }
            }
        }
        role.setPermissionList(permissionList);
        Role role1 = roleService.saveRole(role);
        if (role1 != null) {
            responseVo.setCode(200);
            responseVo.setMsg("修改成功");
        } else {
            responseVo.setCode(403);
            responseVo.setMsg("修改失败");
        }
        return responseVo;
    }

    @PostMapping("/saveGoods")
    @ResponseBody
    public ResponseVo saveGoods(Goods goods) {
        Goods save = goodsService.saveGoods(goods);
        if(save!=null) {
            return new ResponseVo(200, save, "添加成功");
        }else{
            return new ResponseVo(403, null, "添加失败");
        }
    }
    @DeleteMapping("/delGoods")
    @ResponseBody
    public ResponseVo deleteGoods(Integer id){
        Integer integer = goodsService.deleteGoodsById(id);
        if(integer==1) {
            return new ResponseVo(200, null, "删除成功");
        }else {
            return new ResponseVo(403, null, "删除失败");
        }
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public ResponseVo saveUser(User user) {
        User save = userService.saveUser(user);
        if(save!=null) {
            return new ResponseVo(200, save, "添加成功");
        }else{
            return new ResponseVo(403, null, "添加失败");
        }
    }
}
