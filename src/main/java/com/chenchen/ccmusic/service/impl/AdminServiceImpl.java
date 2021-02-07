package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.AdminDao;
import com.chenchen.ccmusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员Service实现类
 * @author chenchen
 */
@Service
public class AdminServiceImpl implements AdminService {

    /**
     * adminDao注入
     */
    @Autowired
    private AdminDao adminDao;

    /**
     * 管理员登录
     * @param name
     * @param password
     * @return
     */
    @Override
    public boolean login(String name, String password) {
        return adminDao.login(name, password) > 0;
    }
}
