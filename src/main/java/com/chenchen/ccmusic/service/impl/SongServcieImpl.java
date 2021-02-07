package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.SongDao;
import com.chenchen.ccmusic.domain.Song;
import com.chenchen.ccmusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲管理实现类
 * @author chenchen
 */
@Service
public class SongServcieImpl implements SongService {

    /**
     * 注入SongDao
     */
    @Autowired
    private SongDao songDao;

    /**
     * 新增
     * @param song
     * @return
     */
    @Override
    public boolean insert(Song song) {
        return songDao.insert(song) > 0;
    }

    /**
     * 更新
     * @param song
     * @return
     */
    @Override
    public boolean update(Song song) {
        return songDao.update(song) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songDao.delete(id) > 0;
    }

    /**
     * 根据id查询歌曲
     * @param id
     * @return
     */
    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songDao.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌曲
     * @return
     */
    @Override
    public List<Song> selectAll() {
        return songDao.selectAll();
    }

    /**
     * 根据名称精确搜索歌曲
     * @param name
     * @return
     */
    @Override
    public Song selectSongByName(String name) {
        return songDao.selectSongByName(name);
    }

    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    @Override
    public List<Song> selectSongLikeName(String name) {
        return songDao.selectSongLikeName("%" + name + "%");
    }

    /**
     * 根据歌手id查询
     * @param singerId
     * @return
     */
    @Override
    public List<Song> selectSongBySingerId(Integer singerId) {
        return songDao.selectSongBySingerId(singerId);
    }
}
