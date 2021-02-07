package com.chenchen.ccmusic.domain;

/**
 * 评分实体类
 * @author chenchen
 */
public class Rank {
    /* id */
    private Integer id;

    /* 歌单id */
    private Integer songSheetId;

    /* 用户id */
    private Integer consumerId;

    /* 评分 */
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongSheetId() {
        return songSheetId;
    }

    public void setSongSheetId(Integer songSheetId) {
        this.songSheetId = songSheetId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
