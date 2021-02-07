package com.chenchen.ccmusic.domain;

import java.io.Serializable;

/**
 * 歌单-歌曲实体类
 * @author chenchen
 */
public class SheetSong implements Serializable {

    private Integer id;     //主键

    private Integer songId; //歌曲id

    private Integer songSheetId; //歌单id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
