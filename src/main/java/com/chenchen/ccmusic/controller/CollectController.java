package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.common.utils.IdWorker;
import com.chenchen.ccmusic.domain.Collect;
import com.chenchen.ccmusic.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收藏Controller
 * @author chenchen
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加收藏
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultEntity addCollect(HttpServletRequest request){

        String userId = request.getParameter("userId");           //用户id
        String type = request.getParameter("type");               //收藏类型（0歌曲1歌单）
        String songId = request.getParameter("songId");           //歌曲id
        if(songId == null || songId.equals("")){
            return new ResultEntity(StatusCode.ERROR, false, "收藏歌曲为空");
        }
        if(collectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId))){
            return new ResultEntity(StatusCode.ACCESSERROR, false, "已收藏");
        }

        //保存到收藏的对象中
        Collect collect = new Collect();
        collect.setId((int)idWorker.nextId());
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(songId));

        boolean flag = collectService.insert(collect);
        if(flag){   //保存成功
            return new ResultEntity(StatusCode.OK, true, "保存成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "收藏失败");
    }

    /**
     * 删除收藏
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResultEntity deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id
        boolean flag = collectService.deleteByUserIdSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        if(flag){   //保存成功
            return new ResultEntity(StatusCode.OK, true, "删除成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "删除失败");
    }

    /**
     * 查询所有收藏
     */
    @RequestMapping(value = "/allCollect",method = RequestMethod.GET)
    public ResultEntity allCollect(HttpServletRequest request){
        List<Collect> collects = collectService.allCollect();
        return new ResultEntity(StatusCode.OK, true, "查询成功", collects);
    }

    /**
     * 查询某个用户的收藏列表
     */
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public ResultEntity collectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");          //用户id
        List<Collect> collects = collectService.collectOfUserId(Integer.parseInt(userId));
        return new ResultEntity(StatusCode.OK, true, "查询成功", collects);
    }

}
