package com.daji.supermarket.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author 大稽
 * @date2018/12/1010:57
 */
@Entity
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = -3656278792683261605L;

    @Id
    @GeneratedValue
    private int permissionId;

    private String permissionName;

    private String url;

    @Transient
    private boolean flag;

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
