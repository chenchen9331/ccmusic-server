package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.ConsumerDao;
import com.chenchen.ccmusic.domain.Consumer;
import com.chenchen.ccmusic.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理Service实现类
 * @author chenchen
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;

    /**
     *增加
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerDao.insert(consumer) > 0;
    }

    /**
     *修改
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerDao.update(consumer) > 0;
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(Integer id) {
        return consumerDao.delete(id) > 0;
    }

    /**
     * 根据主键查询整个对象
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerDao.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<Consumer> selectAllConsumer() {
        return consumerDao.selectAllConsumer();
    }

    /**
     * 验证密码
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerDao.verifyPassword(username, password) > 0;
    }

    /**
     * 根据账号查询
     */
    @Override
    public Consumer getByUsername(String username) {
        return consumerDao.getByUsername(username);
    }

    /**
     * 根据用户名模糊搜索
     * @param username
     * @return
     */
    @Override
    public List<Consumer> selectConsumerByUsername(String username) {
        return consumerDao.selectConsumerLileUsername(username);
    }

}
