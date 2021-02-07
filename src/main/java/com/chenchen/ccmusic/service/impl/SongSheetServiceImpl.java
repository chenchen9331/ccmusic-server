package com.chenchen.ccmusic.service.impl;

import com.chenchen.ccmusic.dao.SongSheetDao;
import com.chenchen.ccmusic.domain.SongSheet;
import com.chenchen.ccmusic.service.SongSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单管理Service实现类
 * @author chenchen
 */
@Service
public class SongSheetServiceImpl implements SongSheetService {

    /**
     * 注入songSheetDao
     */
    @Autowired
    private SongSheetDao songSheetDao;

    /**
     * 新增
     * @param songSheet
     * @return
     */
    @Override
    public boolean insert(SongSheet songSheet) {
        return songSheetDao.insert(songSheet) > 0;
    }

    /**
     * 更新
     * @param songSheet
     * @return
     */
    @Override
    public boolean update(SongSheet songSheet) {
        return songSheetDao.update(songSheet) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songSheetDao.delete(id) > 0;
    }

    /**
     * 根据id查询歌单
     * @param id
     * @return
     */
    @Override
    public SongSheet selectByPrimaryKey(Integer id) {
        return songSheetDao.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     * @return
     */
    @Override
    public List<SongSheet> selectAllSongSheet() {
        return songSheetDao.selectAllSongSheet();
    }

    /**
     * 根据标题精确查询歌单
     * @param title
     * @return
     */
    @Override
    public List<SongSheet> selectSongSheetByTitle(String title) {
        return songSheetDao.selectSongSheetByTitle(title);
    }

    /**
     * 根据标题模糊查询歌单
     * @param title
     * @return
     */
    @Override
    public List<SongSheet> selectSongSheetLikeTitle(String title) {
        return songSheetDao.selectSongSheetLikeTitle(title);
    }

    /**
     * 根据风格模糊查询歌单
     * @param style
     * @return
     */
    @Override
    public List<SongSheet> selectSongSheetLikeStyle(String style) {
        return songSheetDao.selectSongSheetLikeStyle(style);
    }
}
