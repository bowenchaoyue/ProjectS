package service;

import domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增分类
     * @param category
     * @return
     */
    int add(Category category);

    /**
     * 查询分类
     * @param category
     * @return
     */
    List<Category> queryByPage(Category category);

}
