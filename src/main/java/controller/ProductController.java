package controller;

import com.github.pagehelper.PageInfo;
import domain.Category;
import domain.Result;
import domain.Series;
import domain.User;
import domain.dto.ProductDTO;
import domain.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ProductService;
import service.SeriesService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private ProductService productService;

    @Resource
    private SeriesService seriesService;

    /**
     * 上传产品的接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public Result addProduct(HttpServletRequest request,@RequestBody ProductDTO dto){
        dealWithDTO(request,dto);
        productService.add(dto);
        return new Result(true);
    }

    /**
     * 更新产品的接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public Result updateProduct(HttpServletRequest request,@RequestBody ProductDTO dto){
        dealWithDTO(request,dto);
        productService.update(dto);
        return new Result(true);
    }

    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteProduct(HttpServletRequest request,@RequestBody ProductDTO dto){
        dealWithDTO(request,dto);
        productService.delete(dto);
        return new Result(true);
    }



    /**
     * 产品列表
     * @param request
     * @param productDTO
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Result getProductList(HttpServletRequest request,@RequestBody ProductDTO productDTO){
        //获取产品信息
        productDTO.setLang(CHINESE);
        PageInfo<ProductVO> pageInfo = productService.queryByPage(productDTO);
        return new Result(true,pageInfo);
    }

    /**
     * 产品详情页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Result getProductDetail(HttpServletRequest request,Long id){
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        ProductVO productVO = productService.queryById(dto);
        return new Result(true,productVO);
    }

    /**
     * 获取产品系列
     * @return
     */
    @RequestMapping("/getSeries")
    @ResponseBody
    public Result getCategory(){
        Series series = new Series();
        List<Series> categories = seriesService.queryByPage(series);
        return new Result(true,categories);
    }
}
