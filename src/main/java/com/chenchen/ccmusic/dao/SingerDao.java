package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手Dao
 * @author chenchen
 */
@Repository
public interface SingerDao {

    /**
     * 新增歌手
     * @param singer
     * @return
     */
    int insert(Singer singer);

    /**
     * 更新歌手
     * @param singer
     * @return
     */
    int update(Singer singer);

    /**
     * 删除歌手
     * @param id
     * @return
     */
    int delete(Integer id);

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

    Integer getSingerCount();
}
