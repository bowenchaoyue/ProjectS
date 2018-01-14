package controller;


import domain.Category;
import domain.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/english/category")
public class CategoryEController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping("/getCategory")
    @ResponseBody
    public Result getCategory(){
        Category category = new Category();
        List<Category> categories = categoryService.queryByPage(category);
        return new Result(true,categories);
    }
}
