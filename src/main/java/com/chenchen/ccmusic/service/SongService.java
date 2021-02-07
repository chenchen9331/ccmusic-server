package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲管理Servcie
 * @author chenchen
 */
public interface SongService {
    /**
     * 新增
     * @param song
     * @return
     */
    boolean insert(Song song);

    /**
     * 更新
     * @param song
     * @return
     */
    boolean update(Song song);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据id查询歌曲
     * @param id
     * @return
     */
    Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    List<Song> selectAll();

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
    List<Song> selectSongLikeName(String name);

    /**
     * 根据歌手id查询
     * @param singerId
     * @return
     */
    List<Song> selectSongBySingerId(Integer singerId);

}
