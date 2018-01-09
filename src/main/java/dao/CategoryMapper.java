package dao;

import domain.Category;

import java.util.List;

public interface CategoryMapper {
    /**
     * 新增Category
     * @param category
     * @return
     */

    int add(Category category);



    /**
     * 更新类别
     * @param category
     * @return
     */
    int update(Category category);

    /**
     * 查询分类
     * @param category
     * @return
     */
    List<Category> queryByPage(Category category);
}
