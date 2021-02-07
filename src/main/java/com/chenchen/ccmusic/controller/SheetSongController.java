package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.domain.SheetSong;
import com.chenchen.ccmusic.service.SheetSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 歌曲-歌单Controller
 * @author chenchen
 */
@RestController
@RequestMapping("/sheetsong")
public class SheetSongController {

    @Autowired
    private SheetSongService sheetSongService;

    /**
     * 给歌单添加歌曲
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultEntity addSheetSong(HttpServletRequest request){
        //获取前端传来的参数
        String songId = request.getParameter("songId").trim();  //歌曲id
        String songSheetId = request.getParameter("songSheetId").trim(); //歌单id
        SheetSong sheetSong = new SheetSong();
        sheetSong.setSongId(Integer.parseInt(songId));
        sheetSong.setSongSheetId(Integer.parseInt(songSheetId));
        boolean flag = sheetSongService.insert(sheetSong);
        if(flag) {
            return new ResultEntity(StatusCode.OK, true, "新增成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "新增失败");
    }

    /**
     * 根据歌单id查询歌曲
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public ResultEntity selectByPrimaryKey(HttpServletRequest request){
        String songSheetId = request.getParameter("songSheetId");
        List<SheetSong> sheetSongs = sheetSongService.selectSongOfSongListId(Integer.parseInt(songSheetId));
        return new ResultEntity(StatusCode.OK, true, "查询成功", sheetSongs);
    }

    /**
     * 删除歌单里的歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResultEntity delete(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();                 //歌曲id
        String songSheetId = request.getParameter("songSheetId").trim();        //歌单id
        boolean flag = sheetSongService.deleteBySongIdAndSongListId(Integer.parseInt(songId),Integer.parseInt(songSheetId));
        if(flag) {
            return new ResultEntity(StatusCode.OK, true, "删除成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "删除失败");
    }
}
