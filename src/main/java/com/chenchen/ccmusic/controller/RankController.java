package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.common.utils.IdWorker;
import com.chenchen.ccmusic.domain.Rank;
import com.chenchen.ccmusic.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenchen
 */
@RestController
@RequestMapping("/rank")
public class RankController {

    /**
     * 注入RankService
     */
    @Autowired
    private RankService rankService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 歌单评分
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultEntity add(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        String consumerId = request.getParameter("consumerId");
        String score = request.getParameter("score");

        Rank rank = new Rank();
        rank.setId((int) idWorker.nextId());
        rank.setSongSheetId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));

        boolean flag = rankService.add(rank);
        if(flag) {
            return new ResultEntity(StatusCode.OK, true, "评分成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "评分失败");
    }

    /**
     * 歌单评分
     * @param request
     * @return
     */
    @RequestMapping(value = "/avg",method = RequestMethod.GET)
    public ResultEntity rankOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        int avgCore = rankService.avgCore(Integer.parseInt(songListId));
        return new ResultEntity(StatusCode.OK, true, "查询成功", avgCore);
    }
}
