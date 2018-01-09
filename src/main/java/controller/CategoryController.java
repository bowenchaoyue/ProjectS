package controller;


import domain.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String topage(ModelMap map){
        Category category = new Category();
        List<Category> categories = categoryService.queryByPage(category);
        map.put("category",categories);
        return "first";
    }

    @RequestMapping("/getJson")
    @ResponseBody
    public List<Category> getJson(){
        Category category = new Category();
        List<Category> categories = categoryService.queryByPage(category);
        return categories;
    }
}
