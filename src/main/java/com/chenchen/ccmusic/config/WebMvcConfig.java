package com.chenchen.ccmusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 自定义mvc配置
 * @author chenchen
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 解决前后端访问跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    /**
     * 图片重定向
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 解决上线后无法加载外部静态资源问题，若本地运行则不需要
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String gitPath = path.getParentFile().getParentFile().getParent() + File.separator + "logistics";

        // 歌手图片地址
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations(// "file:" + System.getProperty("user.dir")
                                      gitPath
                                              + System.getProperty("file.separator")
                                              + "img"
                                              + System.getProperty("file.separator")
                                              + "singerPic"
                                              + System.getProperty("file.separator"));
        // 歌曲图片地址
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations(// "file:" + System.getProperty("user.dir")
                                      gitPath
                                                + System.getProperty("file.separator")
                                                + "img"
                                                + System.getProperty("file.separator")
                                                + "songPic"
                                                + System.getProperty("file.separator"));
        // 歌单图片地址
        registry.addResourceHandler("/img/songSheetPic/**")
                .addResourceLocations(// "file:" + System.getProperty("user.dir")
                                      gitPath
                                                + System.getProperty("file.separator")
                                                + "img"
                                                + System.getProperty("file.separator")
                                                + "songSheetPic"
                                                + System.getProperty("file.separator"));
        // 歌曲资源地址
        registry.addResourceHandler("/song/**")
                .addResourceLocations(// "file:" + System.getProperty("user.dir")
                                      gitPath
                                                + System.getProperty("file.separator")
                                                + "song"
                                                + System.getProperty("file.separator"));
        //前端用户头像地址
        registry.addResourceHandler("/avatorImages/**").addResourceLocations(
                // "file:"+System.getProperty("user.dir")+
                gitPath + System.getProperty("file.separator")+"avatorImages"+System.getProperty("file.separator")
        );
        //用户头像默认地址
        registry.addResourceHandler("/img/**").addResourceLocations(
                // "file:"+System.getProperty("user.dir")+
                   gitPath + System.getProperty("file.separator")+"img"+System.getProperty("file.separator")
        );

        //内部静态资源文件映射
        registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
    }
}
