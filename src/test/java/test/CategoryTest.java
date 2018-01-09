package test;

import domain.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.CategoryService;

import java.util.List;

public class CategoryTest extends BaseTestController {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void test1(){
        Category category = new Category();
        category.setName("公司资讯");
        categoryService.add(category);
        category.setName("行业资讯");
        categoryService.add(category);
        category.setName("政策法规");
        categoryService.add(category);
    }

    @Test
    public void test2(){
        Category category = new Category();
        categoryService.queryByPage(category);
    }
}
