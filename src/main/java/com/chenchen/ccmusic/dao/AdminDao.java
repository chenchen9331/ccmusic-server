package com.chenchen.ccmusic.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 管理员Dao
 * @author chenchen
 */
@Repository
public interface AdminDao {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    int login(String name, String password);
}
