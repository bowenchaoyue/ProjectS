package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.PictureMapper;
import domain.Picture;
import org.springframework.stereotype.Service;
import service.PictureService;

import javax.annotation.Resource;
import java.util.List;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {


    @Resource
    private PictureMapper pictureMapper;

    public int add(Picture picture) {
        return pictureMapper.add(picture);
    }

    public int update(Picture picture) {
        return pictureMapper.update(picture);
    }

    public PageInfo<Picture> queryByPage(Picture picture) {
        PageHelper.startPage(picture.getPageNum(),picture.getPageSize());
        List<Picture> pictures = pictureMapper.queryByPage(picture);
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(pictures);
        return pageInfo;
    }
}
