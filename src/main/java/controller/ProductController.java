package controller;

import com.github.pagehelper.PageInfo;
import domain.Result;
import domain.dto.ProductDTO;
import domain.vo.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.ProductService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    /**
     * 上传图片的
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/picupload",method = RequestMethod.POST)
    @ResponseBody
    public Result picUpload(@RequestParam( value = "projectUpload", required = false ) MultipartFile file,
                 HttpServletRequest request, HttpServletResponse response){
     return new Result(true);
    }

    //验证码的
    //登录的
    //拦截的



}
