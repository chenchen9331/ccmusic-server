package com.chenchen.ccmusic.service;

import java.util.List;
import java.util.Map;

/**
 * 首页相关Service接口
 * @author chenchen
 */
public interface IndexService {

    /**
     * 获取首页相关总数量（用户、歌手、歌曲、歌单）
     * @return
     */
    Map<String, Integer> getCount();

    /**
     * 获取用户性别数量
     * @return
     */
    List<Integer> getConsumerCount();

    /**
     * 获取歌单风格统计
     * @return
     */
    Map<String, Integer> getSheetSongStyleCount();

    /**
     * 歌手性别统计
     * @return
     */
    List<Integer> getSingerSexCount();

    /**
     * 获取歌手国籍统计
     * @return
     */
    Map<String, Integer> getSingerCountryCount();
}
