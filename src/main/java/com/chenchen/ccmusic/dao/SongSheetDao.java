package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.SongSheet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单管理Dao
 * @author chenchen
 */
@Repository
public interface SongSheetDao {

    /**
     * 增加歌单
     * @param songSheet
     * @return
     */
    int insert(SongSheet songSheet);

    /**
     * 更新歌单
     * @param songSheet
     * @return
     */
    int update(SongSheet songSheet);

    /**
     * 删除歌单
     * @param id
     * @return
     */
    int delete(Integer id);

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

    /**
     * 获取歌单总数量
     */
    Integer getSongSheetCount();
}
