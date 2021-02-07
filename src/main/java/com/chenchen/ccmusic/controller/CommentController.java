package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.common.utils.IdWorker;
import com.chenchen.ccmusic.domain.Comment;
import com.chenchen.ccmusic.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论Comtroller
 * @author chenchen
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加评论
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultEntity addComment(HttpServletRequest request){

        String userId = request.getParameter("userId");           //用户id
        String type = request.getParameter("type");               //评论类型（0歌曲1歌单）
        String songId = request.getParameter("songId");           //歌曲id
        String songListId = request.getParameter("songListId");   //歌单id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId((int)idWorker.nextId());
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(new Byte(type) ==0){
            comment.setSongId(Integer.parseInt(songId));
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if(flag){   //保存成功
            return new ResultEntity(StatusCode.OK, true, "发表成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "发表失败");
    }

    /**
     * 修改评论
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultEntity updateComment(HttpServletRequest request){
        String id = request.getParameter("id").trim();                   //主键
        String userId = request.getParameter("userId").trim();           //用户id
        String type = request.getParameter("type").trim();               //评论类型（0歌曲1歌单）
        String songId = request.getParameter("songId").trim();           //歌曲id
        String songListId = request.getParameter("songListId").trim();   //歌单id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(songId != null && songId.equals("")){
            songId = null;
        }else {
            comment.setSongId(Integer.parseInt(songId));
        }
        if(songListId != null && songListId.equals("")){
            songListId = null;
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);

        boolean flag = commentService.update(comment);
        if(flag){   //保存成功
            return new ResultEntity(StatusCode.OK, true, "修改成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "修改失败");
    }

    /**
     * 删除评论
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResultEntity deleteComment(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = commentService.delete(Integer.parseInt(id));
        if(flag){   //保存成功
            return new ResultEntity(StatusCode.OK, true, "删除成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "删除失败");
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public ResultEntity selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        Comment comment = commentService.selectByPrimaryKey(Integer.parseInt(id));
        return new ResultEntity(StatusCode.OK, true, "查询成功", comment);
    }

    /**
     * 查询所有评论
     */
    @RequestMapping(value = "/allComment",method = RequestMethod.GET)
    public ResultEntity allComment(HttpServletRequest request){
        List<Comment> comments = commentService.allComment();
        return new ResultEntity(StatusCode.OK, true, "查询成功", comments);
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @RequestMapping(value = "/commentOfSongId",method = RequestMethod.GET)
    public ResultEntity commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId");          //歌曲id
        List<Comment> comments = commentService.commentOfSongId(Integer.parseInt(songId));
        return new ResultEntity(StatusCode.OK, true, "查询成功", comments);
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @RequestMapping(value = "/commentOfSongListId",method = RequestMethod.GET)
    public ResultEntity commentOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");          //歌曲id
        List<Comment> comments = commentService.commentOfSongListId(Integer.parseInt(songListId));
        return new ResultEntity(StatusCode.OK, true, "查询成功", comments);
    }

    /**
     * 给某个评论点赞
     */
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public ResultEntity like(HttpServletRequest request){
        String id = request.getParameter("id").trim();           //主键
        String up = request.getParameter("up").trim();           //用户id

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean flag = commentService.update(comment);
        if(flag){   //保存成功
            return new ResultEntity(StatusCode.OK, true, "点赞成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "点赞失败");
    }
}
