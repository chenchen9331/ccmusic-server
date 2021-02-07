package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲Dao
 * @author chenchen
 */
@Repository
public interface SongDao {

    /**
     * 新增
     * @param song
     * @return
     */
    public int insert(Song song);

    /**
     * 更新
     * @param song
     * @return
     */
    public int update(Song song);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 根据id查询歌曲
     * @param id
     * @return
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    public List<Song> selectAll();

    /**
     * 根据名称精确搜索歌曲
     * @param name
     * @return
     */
    Song selectSongByName(String name);

    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    public List<Song> selectSongLikeName(String name);

    /**
     * 根据歌手id查询
     * @param singerId
     * @return
     */
    public List<Song> selectSongBySingerId(Integer singerId);

    /**
     * 获取歌曲总数量
     * @return
     */
    Integer getSongCount();
}
