package service;

import com.github.pagehelper.PageInfo;
import domain.Picture;


public interface PictureService {

    int add(Picture picture);

    int update(Picture picture);

    PageInfo<Picture> queryByPage(Picture picture);
}
