package com.chenchen.ccmusic.service;

import com.chenchen.ccmusic.domain.Comment;

import java.util.List;

/**
 * @author chenchen
 */
public interface CommentService {
    /**
     *增加
     */
    boolean insert(Comment comment);

    /**
     *修改
     */
    boolean update(Comment comment);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    List<Comment> commentOfSongListId(Integer songListId);
}
