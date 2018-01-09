package service.impl;

import dao.CategoryMapper;
import domain.Category;
import org.springframework.stereotype.Service;
import service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public int add(Category category) {
        return categoryMapper.add(category);
    }

    public List<Category> queryByPage(Category category) {
        return categoryMapper.queryByPage(category);
    }
}
