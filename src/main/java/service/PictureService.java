package service;

import com.github.pagehelper.PageInfo;
import domain.Picture;

import java.util.List;


public interface PictureService {

    int add(Picture picture);

    int update(Picture picture);

    PageInfo<Picture> queryByPage(Picture picture);

    List<Picture> query(Picture picture);

    int delete(Picture picture);
}
