package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 首页相关Controller
 * @author chenchen
 */
@RestController
@RequestMapping("/indexPage")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 获取首页相关总数量（用户、歌手、歌曲、歌单）
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResultEntity getCount() {
        Map<String, Integer> countMap = indexService.getCount();
        return new ResultEntity(StatusCode.OK, true, "查询成功", countMap);
    }

    /**
     * 获取用户性别数量
     * @return
     */
    @RequestMapping(value = "/sexCount", method = RequestMethod.GET)
    public ResultEntity getConsumerSexCount() {
        List<Integer> sexCount = indexService.getConsumerCount();
        return new ResultEntity(StatusCode.OK, true, "查询成功", sexCount);
    }

    /**
     * 歌单风格统计
     * @return
     */
    @RequestMapping(value = "/sheetStyleCount", method = RequestMethod.GET)
    public ResultEntity getSheetSongStyleCount() {
        Map<String, Integer> sheetSongStyleCount = indexService.getSheetSongStyleCount();
        return new ResultEntity(StatusCode.OK, true, "查询成功", sheetSongStyleCount);
    }

    /**
     * 歌手性别统计
     * @return
     */
    @RequestMapping(value = "/singerSex", method = RequestMethod.GET)
    public ResultEntity getSingerSexCount() {
        List<Integer> singerSexCount = indexService.getSingerSexCount();
        return new ResultEntity(StatusCode.OK, true, "查询成功", singerSexCount);
    }

    /**
     * 歌手国籍统计
     * @return
     */
    @RequestMapping(value = "/singerCountry", method = RequestMethod.GET)
    public ResultEntity getSingerCountryCount() {
        Map<String, Integer> singerCountryCount = indexService.getSingerCountryCount();
        return new ResultEntity(StatusCode.OK, true, "查询成功", singerCountryCount);
    }
}
