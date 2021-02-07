package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.common.utils.IdWorker;
import com.chenchen.ccmusic.domain.SongSheet;
import com.chenchen.ccmusic.service.SongSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author chenchen
 */
@RestController
@RequestMapping("/songsheet")
public class SongSheetController {

    /**
     * 注入SongSheetService
     */
    @Autowired
    private SongSheetService songSheetService;

    /**
     * id生成器
     */
    @Autowired
    private IdWorker idWorker;

    /**
     * 新增
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultEntity insert(HttpServletRequest request) {

        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        SongSheet songSheet = new SongSheet();
        songSheet.setId((int)idWorker.nextId());
        songSheet.setTitle(title);
        songSheet.setPic(pic);
        songSheet.setIntroduction(introduction);
        songSheet.setStyle(style);

        boolean flag = songSheetService.insert(songSheet);
        if(flag) {
            return new ResultEntity(StatusCode.OK, true, "新增成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "新增失败");
    }

    /**
     * 修改歌单
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultEntity updateSongList(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        String title = request.getParameter("title").trim();      //标题
        String introduction = request.getParameter("introduction").trim();//简介
        String style = request.getParameter("style").trim();    //风格
        //保存到歌单的对象中
        SongSheet songSheet = new SongSheet();
        songSheet.setId(Integer.parseInt(id));
        songSheet.setTitle(title);
        songSheet.setIntroduction(introduction);
        songSheet.setStyle(style);
        boolean flag = songSheetService.update(songSheet);
        if(flag) {
            return new ResultEntity(StatusCode.OK, true, "更新成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "更新失败");
    }

    /**
     * 删除歌单
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResultEntity deleteSongList(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = songSheetService.delete(Integer.parseInt(id));
        if(flag) {
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
        SongSheet songSheet = songSheetService.selectByPrimaryKey(Integer.parseInt(id));
        return new ResultEntity(StatusCode.OK, true, "查询成功", songSheet);
    }

    /**
     * 查询所有歌单
     */
    @RequestMapping(value = "/selectAllSongSheet",method = RequestMethod.GET)
    public ResultEntity selectAllSongSheet(HttpServletRequest request){
        List<SongSheet> songSheets = songSheetService.selectAllSongSheet();
        return new ResultEntity(StatusCode.OK, true, "查询成功", songSheets);
    }

    /**
     * 根据标题精确查询歌单列表
     */
    @RequestMapping(value = "/selectSongSheetByTitle",method = RequestMethod.GET)
    public ResultEntity selectSongSheetByTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();          //歌单标题
        List<SongSheet> songSheets = songSheetService.selectSongSheetByTitle(title);
        return new ResultEntity(StatusCode.OK, true, "查询成功", songSheets);
    }

    /**
     * 根据标题模糊查询歌单列表
     */
    @RequestMapping(value = "/selectSongSheetLikeTitle",method = RequestMethod.GET)
    public ResultEntity selectSongSheetLikeTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();          //歌单标题
        List<SongSheet> songSheets = songSheetService.selectSongSheetLikeTitle("%" + title + "%");
        return new ResultEntity(StatusCode.OK, true, "查询成功", songSheets);
    }

    /**
     * 根据风格模糊查询歌单列表
     */
    @RequestMapping(value = "/selectSongSheetLikeStyle",method = RequestMethod.GET)
    public ResultEntity selectSongSheetLikeStyle(HttpServletRequest request){
        String style = request.getParameter("style").trim();          //歌单风格
        List<SongSheet> songSheets = songSheetService.selectSongSheetLikeStyle("%" + style + "%");
        return new ResultEntity(StatusCode.OK, true, "查询成功", songSheets);
    }

    @RequestMapping(value = "/updateSongSheetPic",method = RequestMethod.POST)
    public ResultEntity updateSongSheetPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) {

        if (avatorFile.isEmpty()) {
            return  new ResultEntity(StatusCode.ERROR, false, "上传失败");
        }

        // 文件名称
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir")
                            + System.getProperty("file.separator")
                            + "img"
                            + System.getProperty("file.separator")
                            + "songSheetPic";
        // 存放路径是否存在,不存在则创建
        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdir();
        }
        // 实际文件路径
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);// 数据库中url路径
        String storeAvatorPath = "/img/songSheetPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongSheet songSheet = new SongSheet();
            songSheet.setId(id);
            songSheet.setPic(storeAvatorPath);
            boolean flag = songSheetService.update(songSheet);
            if(flag) {
                return new ResultEntity(StatusCode.OK, true, "更新成功");
            }
            return new ResultEntity(StatusCode.ERROR, false, "更新失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultEntity(StatusCode.ERROR, false, "更新失败", e.getMessage());
        }
    }
}
