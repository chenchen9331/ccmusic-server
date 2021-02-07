package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.common.utils.IdWorker;
import com.chenchen.ccmusic.domain.Singer;
import com.chenchen.ccmusic.service.SingerService;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 歌手Controller
 * @author chenchen
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    /**
     * 注入SingerService
     */
    @Autowired
    private SingerService singerService;

    /**
     * id生成（雪花算法）
     */
    @Autowired
    private IdWorker idWorker;

    /**
     * 新增歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultEntity addSinger(HttpServletRequest request) {

        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();

        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date();
        try {
            birthday = dataFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Singer singer = new Singer();
        singer.setId((int) idWorker.nextId());
        singer.setName(name);
        singer.setSex(Integer.parseInt(sex));
        singer.setPic(pic);
        singer.setBirth(birthday);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        Boolean flag = singerService.insert(singer);

        if (flag) {
            return new ResultEntity(StatusCode.OK, true, "添加成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "添加失败");
    }

    /**
     * 更新歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultEntity updateSinger(HttpServletRequest request) {

        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();

        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date();
        try {
            birthday = dataFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(Integer.parseInt(sex));
        singer.setBirth(birthday);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        Boolean flag = singerService.update(singer);

        if (flag) {
            return new ResultEntity(StatusCode.OK, true, "更新成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "更新失败");
    }

    /**
     * 删除歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultEntity deleteSinger(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Boolean flag = singerService.delete(id);
        if (flag) {
            return new ResultEntity(StatusCode.OK, true, "删除成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "删除失败");
    }

    /**
     * 根据id查询歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public ResultEntity findSingerByPrimaryKey(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Singer singer = singerService.selectByPrimaryKey(id);
        return new ResultEntity(StatusCode.OK, true, "查询成功", singer);
    }

    /**
     * 查询所有歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public ResultEntity findAllSinger(HttpServletRequest request) {
        List<Singer> singerList = singerService.selectAll();
        return new ResultEntity(StatusCode.OK, true, "查询成功", singerList);
    }

    /**
     * 根据姓名查询歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByName", method = RequestMethod.GET)
    public ResultEntity findSingerByName(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        List<Singer> singerList = singerService.selectSingerByName(name);
        return new ResultEntity(StatusCode.OK, true, "查询成功", singerList);
    }

    /**
     * 根据性别查询歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectBySex", method = RequestMethod.GET)
    public ResultEntity findSingerBySex(HttpServletRequest request) {
        String sex = request.getParameter("sex");
        List<Singer> singerList = singerService.selectSingerBySex(Integer.parseInt(sex));
        return new ResultEntity(StatusCode.OK, true, "查询成功", singerList);
    }

    /**
     * 更新歌手头像
     * @param avatorFile
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateAvator", method = RequestMethod.POST)
    public ResultEntity updateAvator(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") Integer id) {
        if (avatorFile.isEmpty()) {
            return new ResultEntity(StatusCode.ERROR, false, "文件上传失败");
        }
        // 文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        // 如果文件路径不存则创建
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 数据库中相对文件地址
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
            if (flag) {
                return new ResultEntity(StatusCode.OK, true, "更新成功", storeAvatorPath);
            }
            return new ResultEntity(StatusCode.ERROR, false, "更新失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultEntity(StatusCode.ERROR, false, "上传失败", e.getMessage());
        }
    }
}
