package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收藏Service
 * @author chenchen
 */
public interface CollectService {
    /**
     *增加
     */
    boolean insert(Collect collect);

    /**
     * 删除
     */
   boolean delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    boolean deleteByUserIdSongId(Integer userId, Integer songId);

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
    boolean existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);
}
