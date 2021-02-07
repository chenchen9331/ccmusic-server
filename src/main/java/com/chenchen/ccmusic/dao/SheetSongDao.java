package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.SheetSong;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单-歌曲Dao
 * @author chenchen
 */
@Repository
public interface SheetSongDao {
    /**
     *增加
     */
    int insert(SheetSong listSong);

    /**
     *修改
     */
    int update(SheetSong listSong);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据歌曲id和歌单id删除
     */
    int deleteBySongIdAndSongListId(Integer songId,Integer songListId);

    /**
     * 根据主键查询整个对象
     */
    SheetSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单里面的歌曲
     */
    List<SheetSong> allListSong();

    /**
     * 根据歌单id查询所有的歌曲
     */
    List<SheetSong> listSongOfSongListId(Integer songListId);
}
