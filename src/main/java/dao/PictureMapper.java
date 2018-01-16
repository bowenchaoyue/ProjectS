package dao;

import domain.Picture;

import java.util.List;

public interface PictureMapper {
    /**
     * 新增图片
     * @param picture
     * @return
     */
    int add(Picture picture);

    /**
     * 更新图片
     * @param picture
     * @return
     */
    int update(Picture picture);

    /**
     * 分页查询图片
     * @param picture
     * @return
     */
    List<Picture> queryByPage(Picture picture);

    /**
     * 删除图片
     * @param picture
     * @return
     */
    int delete(Picture picture);
}
