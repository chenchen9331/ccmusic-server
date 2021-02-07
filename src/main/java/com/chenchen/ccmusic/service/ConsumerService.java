package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.Consumer;

import java.util.List;

/**
 * 用户管理Service
 * @author chenchen
 */
public interface ConsumerService {

    /**
     *增加
     */
    boolean insert(Consumer consumer);

    /**
     *修改
     */
    boolean update(Consumer consumer);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     */
    List<Consumer> selectAllConsumer();

    /**
     * 查看密码是否正确
     */
    boolean verifyPassword(String username,String password);

    /**
     * 根据账号查询
     */
    Consumer getByUsername(String username);

    /**
     * 根据用户名模糊搜索
     */
    List<Consumer> selectConsumerByUsername(String username);

}
