package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.SheetSong;

import java.util.List;

/**
 * 歌曲-歌单Service
 * @author chenchen
 */
public interface SheetSongService {
    /**
     *增加
     */
    public boolean insert(SheetSong listSong);

    /**
     *修改
     */
    public boolean update(SheetSong listSong);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据歌曲id和歌单id删除
     */
    public boolean deleteBySongIdAndSongListId(Integer songId,Integer songListId);

    /**
     * 根据主键查询整个对象
     */
    public SheetSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单里面的歌曲
     */
    public List<SheetSong> selectAllListSong();

    /**
     * 根据歌单id查询所有的歌曲
     */
    public List<SheetSong> selectSongOfSongListId(Integer songListId);
}
