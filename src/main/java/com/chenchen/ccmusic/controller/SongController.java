package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.common.utils.IdWorker;
import com.chenchen.ccmusic.domain.Song;
import com.chenchen.ccmusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 歌曲管理Controller
 * @author chenchen
 */
@RestController
@RequestMapping("/song")
public class SongController {

    /**
     * 歌曲Service注入
     */
    @Autowired
    private SongService songService;

    /**
     * id生成
     */
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加歌曲
     * @param request
     * @param mpFile
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultEntity insertSong(HttpServletRequest request, @RequestParam("file") MultipartFile mpFile) {
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = request.getParameter("lyric").trim();


        // 歌曲上传
        if (mpFile.isEmpty()) {
            return new ResultEntity(StatusCode.ERROR, false, "歌曲上传失败");
        }
        // 文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        // 若不存在文件路径则创建
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 储存到数据库中相对地址
        String storeUrlPath = "/song/" + fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setId((int) idWorker.nextId());
            song.setName(name);
            song.setSingerId(Integer.parseInt(singerId));
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(song);
            if (flag) {
                return new ResultEntity(StatusCode.OK, true, "新增成功", storeUrlPath);
            }
            return new ResultEntity(StatusCode.ERROR, false, "新增失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultEntity(StatusCode.ERROR, false, "新增失败", e.getMessage());
        }
    }

    /**
     * 根据歌手id获取歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
    public ResultEntity selectSongBySingerId(HttpServletRequest request) {
        String singerId = request.getParameter("singerId");
        List<Song> songs = songService.selectSongBySingerId(Integer.parseInt(singerId));
        return new ResultEntity(StatusCode.OK, true, "查询成功", songs);
    }

    /**
     * 根据歌曲id获取歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public ResultEntity selectByPrimaryKey(HttpServletRequest request) {
        String songId = request.getParameter("songId");
        Song song = songService.selectByPrimaryKey(Integer.parseInt(songId));
        return new ResultEntity(StatusCode.OK, true, "查询成功", song);
    }

    /**
     * 根据歌曲姓名模糊查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResultEntity selectSongLikeName(HttpServletRequest request) {
        String name = request.getParameter("name");
        List<Song> songs = songService.selectSongLikeName("%" + name + "%");
        return new ResultEntity(StatusCode.OK, true, "查询成功", songs);
    }

    /**
     * 根据歌曲姓名精确查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectSongByName", method = RequestMethod.GET)
    public ResultEntity selectSongByName(HttpServletRequest request) {
        String name = request.getParameter("name");
        Song songs = songService.selectSongByName(name);
        return new ResultEntity(StatusCode.OK, true, "查询成功", songs);
    }

    /**
     * 更新歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultEntity update(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String introduction = request.getParameter("introduction");
        String lyric = request.getParameter("lyric");
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);

        Date date = new Date(System.currentTimeMillis());
        song.setUpdateTime(date);
        boolean flag = songService.update(song);
        if (flag) {
            return new ResultEntity(StatusCode.OK, true, "更新成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "更新失败");
    }

    /**
     * 删除歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultEntity delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        boolean flag = songService.delete(Integer.parseInt(id));
        if (flag) {
            return new ResultEntity(StatusCode.OK, true, "删除成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "删除失败");
    }

    /**
     * 更新歌曲图片
     * @param songPic
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateAvator",method = RequestMethod.POST)
    public ResultEntity updateSongPic(@RequestParam("file") MultipartFile songPic, @RequestParam("id") Integer id) {
        if (songPic.isEmpty()) {
            return new ResultEntity(StatusCode.ERROR, false, "歌曲图片上传失败");
        }
        // 文件名称
        String filename = System.currentTimeMillis() + songPic.getOriginalFilename();
        // 文件路径
        String filepath = System.getProperty("user.dir") + System.getProperty("file.separator")  + "img" + System.getProperty("file.separator") + "songPic";
        // 是否存在该路径，不存在创建
        File filePath = new File(filepath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        // 实际文件地址
        String dest = filepath + System.getProperty("file.separator") + filename;
        // 文件在数据库中相对地址
        String storePath = "/img/songPic/" + filename;
        File file = new File(dest);
        try {
            songPic.transferTo(file);
            Song song = new Song();
            song.setId(id);
            song.setPic(storePath);
            boolean flag = songService.update(song);
            if (flag) {
                return new ResultEntity(StatusCode.OK, true, "更新成功");
            }
            return new ResultEntity(StatusCode.ERROR, false, "更新失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultEntity(StatusCode.OK, true, "更新歌曲图片失败", e.getMessage());
        }
    }

    /**
     * 更新歌曲url
     * @param songUrl
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongUrl",method = RequestMethod.POST)
    public ResultEntity updateSongUrl(@RequestParam("file") MultipartFile songUrl, @RequestParam("id") Integer id) {
        if (songUrl.isEmpty()) {
            return new ResultEntity(StatusCode.ERROR, false, "歌曲图片上传失败");
        }
        // 文件名称
        String filename = System.currentTimeMillis() + songUrl.getOriginalFilename();
        // 文件路径
        String filepath = System.getProperty("user.dir") + System.getProperty("file.separator")  + "song";
        // 是否存在该路径，不存在创建
        File filePath = new File(filepath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        // 实际文件地址
        String dest = filepath + System.getProperty("file.separator") + filename;
        // 文件在数据库中相对地址
        String storePath = "/song/" + filename;
        File file = new File(dest);
        try {
            songUrl.transferTo(file);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storePath);
            boolean flag = songService.update(song);
            if (flag) {
                return new ResultEntity(StatusCode.OK, true, "更新成功");
            }
            return new ResultEntity(StatusCode.ERROR, false, "更新失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultEntity(StatusCode.OK, true, "更新歌曲图片失败", e.getMessage());
        }
    }
}
