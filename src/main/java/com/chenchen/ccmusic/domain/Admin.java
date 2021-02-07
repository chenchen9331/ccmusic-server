package com.chenchen.ccmusic.domain;

import java.io.Serializable;

/**
 * 管理员实体类
 * @author chenchen
 */
public class Admin implements Serializable {

    // Id
    private Integer id;

    // 用户名
    private String name;

    // 密码
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
