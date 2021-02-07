package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户管理Dao
 * @author chenchen
 */
@Repository
public interface ConsumerDao {
    /**
     *增加
     */
    int insert(Consumer consumer);

    /**
     *修改
     */
    int update(Consumer consumer);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     */
    List<Consumer> selectAllConsumer();

    /**
     * 验证密码
     */
    int verifyPassword(String username,String password);

    /**
     * 根据账号查询
     */
    Consumer getByUsername(String username);

    /**
     * 根据用户名模糊查询用
     */
    List<Consumer> selectConsumerLileUsername(String username);

    /**
     * 获取用户总数量
     * @return
     */
    Integer getConsumerCount();
}
