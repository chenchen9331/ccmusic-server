package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.common.utils.IdWorker;
import com.chenchen.ccmusic.domain.Consumer;
import com.chenchen.ccmusic.service.ConsumerService;
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
 * 用户管理Controller
 * @author chenchen
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加前端用户
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultEntity addConsumer(HttpServletRequest request){
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区
        String avator = request.getParameter("avator").trim();          //头像地址

        if( username == null || username.equals("") || password == null || password.equals("")){

            return new ResultEntity(StatusCode.ERROR, false, "用户名或密码不能为空");
        }

        Consumer consumer1 = consumerService.getByUsername(username);
        if(consumer1!=null){

            return new ResultEntity(StatusCode.ERROR, false, "用户名已存在");
        }

        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId((int) idWorker.nextId());
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        boolean flag = consumerService.insert(consumer);
        if(flag){   //保存
            return new ResultEntity(StatusCode.OK, false, "注册成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "注册失败");
    }

    /**
     * 修改前端用户
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultEntity updateConsumer(HttpServletRequest request){

        String id = request.getParameter("id").trim();          //主键
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区

        if( username == null || username.equals("") || password == null || password.equals("")){

            return new ResultEntity(StatusCode.ERROR, false, "用户名或密码不能为空");
        }
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        boolean flag = consumerService.update(consumer);
        if(flag){   //保存
            return new ResultEntity(StatusCode.OK, true, "修改成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "修改失败");
    }


    /**
     * 删除前端用户
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResultEntity deleteConsumer(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = consumerService.delete(Integer.parseInt(id));
        if(flag){   //保存
            return new ResultEntity(StatusCode.OK, true, "修改成功");
        }
        return new ResultEntity(StatusCode.ERROR, false, "修改失败");
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public ResultEntity selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        Consumer consumer = consumerService.selectByPrimaryKey(Integer.parseInt(id));
        return new ResultEntity(StatusCode.OK, true, "查询成功", consumer);
    }

    /**
     * 查询所有前端用户
     */
    @RequestMapping(value = "/allConsumer",method = RequestMethod.GET)
    public ResultEntity allConsumer(HttpServletRequest request){
        List<Consumer> consumers = consumerService.selectAllConsumer();
        return new ResultEntity(StatusCode.OK, true, "查询成功", consumers);
    }

    /**
     * 根据用户名模糊搜索
     */
    @RequestMapping(value = "/selectConsumerByUsername",method = RequestMethod.GET)
    public ResultEntity selectConsumerByUsername(HttpServletRequest request){
        String name = request.getParameter("name");
        List<Consumer> consumers = consumerService.selectConsumerByUsername("%" + name + "%");
        return new ResultEntity(StatusCode.OK, false, "查询成功", consumers);
    }

    /**
     * 更新前端用户图片
     */
    @RequestMapping(value = "/updateConsumerPic",method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        if(avatorFile.isEmpty()){
            return new ResultEntity(StatusCode.ERROR, false, "上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/avatorImages/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean flag = consumerService.update(consumer);
            if(flag){   //保存
                return new ResultEntity(StatusCode.OK, false, "修改成功");
            }
            return new ResultEntity(StatusCode.ERROR, false, "修改失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultEntity(StatusCode.ERROR, false, "修改失败", e.getMessage());
        }
    }

    /**
     * 前端用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultEntity login(HttpServletRequest request){

        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        if( username == null || username.equals("") || password == null || password.equals("")){

            return new ResultEntity(StatusCode.ERROR, false, "用户名或密码不能为空");
        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        boolean flag = consumerService.verifyPassword(username,password);
        if(flag){   //保存
            Consumer comsumer = consumerService.getByUsername(username);
            return new ResultEntity(StatusCode.OK, false, "登录成功", comsumer);
        }
        return new ResultEntity(StatusCode.ERROR, false, "用户名或密码错误");
    }
}
