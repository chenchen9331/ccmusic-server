package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.*;
import com.chenchen.ccmusic.domain.Consumer;
import com.chenchen.ccmusic.domain.Singer;
import com.chenchen.ccmusic.domain.SongSheet;
import com.chenchen.ccmusic.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 首页相关Servcie
 * @author chenchen
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private SingerDao singerDao;

    @Autowired
    private SongDao songDao;

    @Autowired
    private SongSheetDao songSheetDao;

    @Override
    public Map<String, Integer> getCount() {
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("consumerCount", consumerDao.getConsumerCount());
        countMap.put("songCount", songDao.getSongCount());
        countMap.put("singerCount", singerDao.getSingerCount());
        countMap.put("songListCount", songSheetDao.getSongSheetCount());

        return countMap;
    }

    /**
     * 获取用户性别数量
     * @return
     */
    @Override
    public List<Integer> getConsumerCount() {
        int man = 0;
        int woman = 0;
        List<Consumer> consumers = consumerDao.selectAllConsumer();
        for (Consumer consumer : consumers) {
            if (consumer.getSex() == 0) {
                woman ++;
            } else if (consumer.getSex() == 1) {
                man ++;
            }
        }
        List<Integer> count = new LinkedList<>();
        count.add(man);
        count.add(woman);
        return count;
    }

    /**
     * 获取歌单风格统计
     * @return
     */
    @Override
    public Map<String, Integer> getSheetSongStyleCount() {
        Map<String, Integer> styleMap = new HashMap<>();
        styleMap.put("华语", 0);
        styleMap.put("粤语", 0);
        styleMap.put("欧美", 0);
        styleMap.put("日韩", 0);
        styleMap.put("BGM", 0);
        styleMap.put("轻音乐", 0);
        styleMap.put("乐器", 0);
        List<SongSheet> songSheets = songSheetDao.selectAllSongSheet();
        if (songSheets != null && !songSheets.isEmpty()) {

            Integer num1 = 0;
            Integer num2 = 0;
            Integer num3 = 0;
            Integer num4 = 0;
            Integer num5 = 0;
            Integer num6 = 0;
            Integer num7 = 0;

            for (SongSheet songSheet : songSheets) {
                switch (songSheet.getStyle()) {
                    case "华语" :
                        num1 = num1 + 1;
                        break;
                    case "粤语" :
                        num2 = num2 + 1;
                        break;
                    case "欧美" :
                        num3 = num3 + 1;
                        break;
                    case "日韩" :
                        num4 = num4 + 1;
                        break;
                    case "BGM" :
                        num5 = num5 + 1;
                        break;
                    case "轻音乐" :
                        num6 = num6 + 1;
                        break;
                    default :
                        num7 = num7 + 1;
                }
            }
            styleMap.put("华语", num1);
            styleMap.put("粤语", num2);
            styleMap.put("欧美", num3);
            styleMap.put("日韩", num4);
            styleMap.put("BGM", num5);
            styleMap.put("轻音乐", num6);
            styleMap.put("乐器", num7);
            return styleMap;
        }
        return styleMap;
    }

    @Override
    public List<Integer> getSingerSexCount() {
        int man = 0;
        int woman = 0;
        int team = 0;
        List<Singer> singers = singerDao.selectAll();
        for (Singer singer : singers) {
            if (singer.getSex() == 0) {
                woman ++;
            } else if (singer.getSex() == 1) {
                man ++;
            } else {
                team ++;
            }
        }
        List<Integer> count = new LinkedList<>();
        count.add(man);
        count.add(woman);
        count.add(team);
        return count;
    }

    /**
     * 获取歌手国籍统计
     * @return
     */
    @Override
    public Map<String, Integer> getSingerCountryCount() {
        Map<String, Integer> countryMap = new HashMap<>();
        countryMap.put("中国", 0);
        countryMap.put("韩国", 0);
        countryMap.put("日本", 0);
        countryMap.put("美国", 0);
        countryMap.put("新加坡", 0);
        countryMap.put("意大利", 0);
        countryMap.put("其他", 0);
        List<SongSheet> songSheets = songSheetDao.selectAllSongSheet();
        List<Singer> singers = singerDao.selectAll();
        if (singers != null && !singers.isEmpty()) {

            Integer num1 = 0;
            Integer num2 = 0;
            Integer num3 = 0;
            Integer num4 = 0;
            Integer num5 = 0;
            Integer num6 = 0;
            Integer num7 = 0;

            for (Singer singer : singers) {
                switch (singer.getLocation()) {
                    case "中国" :
                        num1 = num1 + 1;
                        break;
                    case "韩国" :
                        num2 = num2 + 1;
                        break;
                    case "日本" :
                        num3 = num3 + 1;
                        break;
                    case "美国" :
                        num4 = num4 + 1;
                        break;
                    case "新加坡" :
                        num5 = num5 + 1;
                        break;
                    case "意大利" :
                        num6 = num6 + 1;
                        break;
                    default :
                        num7 = num7 + 1;
                }
            }
            countryMap.put("中国", num1);
            countryMap.put("韩国", num2);
            countryMap.put("日本", num3);
            countryMap.put("美国", num4);
            countryMap.put("新加坡", num5);
            countryMap.put("意大利", num6);
            countryMap.put("其他", num7);
            return countryMap;
        }
        return countryMap;
    }
}
