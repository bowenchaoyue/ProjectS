package test;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import domain.dto.ProductDTO;
import domain.vo.ProductVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.ProductService;

import java.util.List;

public class ProductTest extends BaseTestController {
    @Autowired
    private ProductService productService;

    @Test
    public void test1(){
        Byte series = 0;
        ProductDTO dto = new ProductDTO();
        dto.setName("C产品");
        dto.setDetail("本产品如何如何……");
        dto.setIntroduction("来自什么什么……");
        dto.setPrice(12.56);
        dto.setSeries(series);
        List<String> strings = Lists.newArrayList();
        strings.add("http://abcdefgBBBB.jpg");
        strings.add("http://zdcssecBBBB.jpg");
        dto.setPicurls(strings);
        productService.add(dto);
    }

    @Test
    public void test2(){
        Byte series = 2;
        ProductDTO dto = new ProductDTO();
        dto.setId(7L);
        dto.setName("C产品编辑一下");
        dto.setDetail("本产品如何如何……");
        dto.setIntroduction("来自什么什么……");
        dto.setPrice(12.56);
        dto.setSeries(series);
        List<String> strings = Lists.newArrayList();
        strings.add("http://abcdefgBBBBbianji.jpg");
        strings.add("http://zdcssecBBBBbianji.jpg");
        dto.setPicurls(strings);
        productService.update(dto);
    }

    @Test
    public void test3(){
        ProductDTO dto = new ProductDTO();
        dto.setPageNum(1);
        dto.setPageSize(5);
        PageInfo<ProductVO> pageInfo = productService.queryByPage(dto);
    }

    @Test
    public void test4(){
        ProductDTO dto = new ProductDTO();
        dto.setId(5l);
        productService.delete(dto);
    }

}
