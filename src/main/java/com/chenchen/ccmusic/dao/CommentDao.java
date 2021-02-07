package com.chenchen.ccmusic.dao;

import com.chenchen.ccmusic.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论实体类
 * @author chenchen
 */
@Repository
public interface CommentDao {
    /**
     *增加
     */
    int insert(Comment comment);

    /**
     *修改
     */
    int update(Comment comment);

    /**
     * 删除
     */
    int delete(Integer id);

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
