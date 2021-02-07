package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏Dao
 * @author chenchen
 */
@Repository
public interface CollectDao {
    /**
     *增加
     */
    int insert(Collect collect);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    int deleteByUserIdSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    /**
     * 查询所有收藏
     */
    List<Collect> allCollect();

    /**
     * 查询某个用户的收藏列表
     */
    List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     */
    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

}
