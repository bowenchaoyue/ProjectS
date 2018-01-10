package controller;

import com.github.pagehelper.PageInfo;
import domain.dto.ProductDTO;
import domain.vo.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ProductService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 到产品首页
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public String toProductPage(ModelMap map){

        ProductDTO dto = new ProductDTO();
        dto.setPageNum(1);
        dto.setPageSize(5);
        PageInfo<ProductVO> pageInfo = productService.queryByPage(dto);

        map.put("pageInfo",pageInfo);
        return "product";
    }


}
