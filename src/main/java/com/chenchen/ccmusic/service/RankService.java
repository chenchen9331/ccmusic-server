package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.Rank;

/**
 * @author chenchen
 */
public interface RankService {

    /**
     * 新增评分
     * @param rank
     * @return
     */
    boolean add(Rank rank);

    /**
     * 歌单评分均值
     * @param songSheetId
     * @return
     */
    int avgCore(Integer songSheetId);

    /**
     * 评分该歌单用户总数
     * @param songSheetId
     * @return
     */
    int rankUserSum(Integer songSheetId);
}
