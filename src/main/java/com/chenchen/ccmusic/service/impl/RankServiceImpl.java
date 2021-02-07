package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.RankDao;
import com.chenchen.ccmusic.domain.Rank;
import com.chenchen.ccmusic.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenchen
 */
@Service
public class RankServiceImpl implements RankService {

    /**
     * RankDao注入
     */
    @Autowired
    private RankDao rankDao;

    /**
     * 新增歌单评分
     * @param rank
     * @return
     */
    @Override
    public boolean add(Rank rank) {
        return rankDao.insert(rank) > 0;
    }

    /**
     * 歌单评分均值
     * @param songSheetId
     * @return
     */
    @Override
    public int avgCore(Integer songSheetId) {

        int userSum = rankDao.selectSumUser(songSheetId);
        if (userSum == 0) {
            return 5;
        }
        int coreSum = rankDao.selectSumRank(songSheetId);
        return coreSum / userSum;
    }

    /**
     * 评分该歌单用户总数
     * @param songSheetId
     * @return
     */
    @Override
    public int rankUserSum(Integer songSheetId) {
        return rankDao.selectSumUser(songSheetId);
    }
}
