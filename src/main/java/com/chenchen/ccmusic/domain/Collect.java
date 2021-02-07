package com.chenchen.ccmusic.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏实体类
 * @author chenchen
 */
public class Collect implements Serializable {

    private Integer id;     //主键
    private Integer userId; //用户id
    private Byte type;      //收藏类型（0歌曲1歌单）
    private Integer songId; //歌曲id
    private Integer songSheetId; //歌单id
    private Date createTime;    //收藏时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongSheetId() {
        return songSheetId;
    }

    public void setSongSheetId(Integer songSheetId) {
        this.songSheetId = songSheetId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
