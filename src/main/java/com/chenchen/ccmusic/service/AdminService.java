package com.chenchen.ccmusic.service;

/**
 * 管理员Servcie接口
 * @author chenchen
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param name
     * @param password
     * @return
     */
    boolean login (String name, String password);
}
