package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.SingerDao;
import com.chenchen.ccmusic.domain.Singer;
import com.chenchen.ccmusic.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手Service实现类
 * @author chenchen
 */
@Service
public class SingerServiceImpl implements SingerService {

    /**
     * 注入SingerDao
     */
    @Autowired
    private SingerDao singerDao;

    /**
     * 新增歌手
     * @param singer
     * @return
     */
    @Override
    public boolean insert(Singer singer) {
        return singerDao.insert(singer) > 0;
    }

    /**
     * 更新歌手
     * @param singer
     * @return
     */
    @Override
    public boolean update(Singer singer) {
        return singerDao.update(singer) > 0;
    }

    /**
     * 删除歌手
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return singerDao.delete(id) > 0;
    }

    /**
     * 根据id查询歌手
     * @param id
     * @return
     */
    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return singerDao.selectByPrimaryKey(id);
    }

    /**
     * 查询全部歌手
     * @return
     */
    @Override
    public List<Singer> selectAll() {
        return singerDao.selectAll();
    }

    /**
     * 根据姓名模糊搜索
     * @param name
     * @return
     */
    @Override
    public List<Singer> selectSingerByName(String name) {
        return singerDao.selectSingerByName("%" + name + "%");
    }

    /**
     * 根据性别查询歌手
     * @param sex
     * @return
     */
    @Override
    public List<Singer> selectSingerBySex(Integer sex) {
        return singerDao.selectSingerBySex(sex);
    }
}
