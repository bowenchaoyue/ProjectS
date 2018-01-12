package controller;

import com.github.pagehelper.PageInfo;
import domain.Result;
import domain.dto.ProductDTO;
import domain.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

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


    @RequestMapping("/list")
    public Result toInfomationList(ProductDTO dto){
        PageInfo<ProductVO> pageInfo = productService.queryByPage(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/detail")
    public Result toInfomationDetail(Long id){
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        ProductVO productVO = productService.queryById(dto);
        return new Result(true,productVO);
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

    /**
     * 产品列表
     * @param request
     * @param productDTO
     * @param map
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public String getProductList(HttpServletRequest request,ProductDTO productDTO,ModelMap map){
        //获取产品信息
        PageInfo<ProductVO> pageInfo = productService.queryByPage(productDTO);
        map.put("pageInfo",pageInfo);
        return "productList";
    }

    /**
     * 产品详情页面
     * @param request
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/detail")
    public String getProductDetail(HttpServletRequest request,Long id,ModelMap map){
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        ProductVO productVO = productService.queryById(dto);
        map.put("product",productVO);
        return "productDetail";
    }


}
