package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.SongSheet;

import java.util.List;

/**
 * 歌单管理Service
 * @author chenchen
 */
public interface SongSheetService {

    /**
     * 新增
     */
    public boolean insert(SongSheet songList);

    /**
     *修改
     */
    public boolean update(SongSheet songList);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public SongSheet selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     */
    public List<SongSheet> selectAllSongSheet();

    /**
     * 根据标题精确查询歌单列表
     */
    public List<SongSheet> selectSongSheetByTitle(String title);

    /**
     * 根据标题模糊查询歌单列表
     */
    public List<SongSheet> selectSongSheetLikeTitle(String title);

    /**
     * 根据风格模糊查询歌单列表
     */
    public List<SongSheet> selectSongSheetLikeStyle(String style);
}
