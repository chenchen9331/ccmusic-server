package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.Singer;

import java.util.List;

/**
 * @author chenchen
 */
public interface SingerService {
    /**
     * 新增歌手
     * @param singer
     * @return
     */
    boolean insert(Singer singer);

    /**
     * 更新歌手
     * @param singer
     * @return
     */
    boolean update(Singer singer);

    /**
     * 删除歌手
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据id查询歌手
     * @param id
     * @return
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * 查询全部歌手
     * @return
     */
    List<Singer> selectAll();

    /**
     * 根据姓名模糊搜索
     * @param name
     * @return
     */
    List<Singer> selectSingerByName(String name);

    /**
     * 根据性别查询歌手
     * @param sex
     * @return
     */
    List<Singer> selectSingerBySex(Integer sex);
}
