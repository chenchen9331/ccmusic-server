package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.Rank;
import org.springframework.stereotype.Repository;

/**
 * 评分Dao
 * @author chenchen
 */
@Repository
public interface RankDao {

    /**
     * 新增评分
     * @param rank
     * @return
     */
    int insert(Rank rank);

    /**
     * 歌单总评分
     * @param songSheetId
     * @return
     */
    int selectSumRank(Integer songSheetId);

    /**
     * 歌单评论总人数
     * @param songSheetId
     * @return
     */
    int selectSumUser(Integer songSheetId);
}
