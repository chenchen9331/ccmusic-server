package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.SheetSongDao;
import com.chenchen.ccmusic.domain.SheetSong;
import com.chenchen.ccmusic.service.SheetSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲-歌单Service实现类
 * @author chenchen
 */
@Service
public class SheetSongServiceImpl implements SheetSongService {

    @Autowired
    private SheetSongDao sheetSongDao;

    /**
     *增加
     */
    @Override
    public boolean insert(SheetSong listSong) {
        return sheetSongDao.insert(listSong) > 0;
    }

    /**
     *修改
     */
    @Override
    public boolean update(SheetSong listSong) {
        return sheetSongDao.update(listSong) > 0;
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(Integer id) {
        return sheetSongDao.delete(id) > 0;
    }

    /**
     * 根据歌曲id和歌单id删除
     */
    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        return sheetSongDao.deleteBySongIdAndSongListId(songId, songListId) > 0;
    }

    /**
     * 根据主键查询整个对象
     */
    @Override
    public SheetSong selectByPrimaryKey(Integer id) {
        return sheetSongDao.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单里面的歌曲
     */
    @Override
    public List<SheetSong> selectAllListSong() {
        return sheetSongDao.allListSong();
    }

    /**
     * 根据歌单id查询所有的歌曲
     */
    @Override
    public List<SheetSong> selectSongOfSongListId(Integer songListId) {
        return sheetSongDao.listSongOfSongListId(songListId);
    }
}
