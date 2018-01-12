package dao;

import domain.Series;

import java.util.List;

public interface SeriesMapper {
    /**
     * 新增Category
     * @return
     */

    int add(Series series);

    /**
     * 更新类别
     * @return
     */
    int update(Series series);

    /**
     * 查询分类
     * @return
     */
    List<Series> queryByPage(Series series);
}
