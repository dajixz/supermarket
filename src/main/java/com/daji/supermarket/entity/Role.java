package com.daji.supermarket.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1010:52
 */
@Entity
@Data
public class Role implements Serializable{
    private static final long serialVersionUID = 3745311606693370339L;
    @Id
    @GeneratedValue
    private int roleId;
    private String roleName;
    private String roleDescription;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "permission_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Permission> permissionList;

    @Transient
    private boolean flag;
    @Transient
    private List<Integer> permissions;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}
