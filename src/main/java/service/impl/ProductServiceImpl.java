package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import dao.ProductMapper;
import dao.ProductPicMapper;
import domain.ProductPic;
import domain.dto.ProductDTO;
import domain.vo.ProductVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProductService;
import utils.CommonUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductPicMapper productPicMapper;


    @Transactional
    public int add(ProductDTO dto) {
        //再存储商品信息
        productMapper.add(dto);
        //先存储商品图片关联关系
        List<ProductPic> productPics = Lists.newArrayList();
        List<String> picurls = dto.getPicurls();
        //构建商品图片对应关系
        if (!CollectionUtils.isEmpty(picurls)){
            for (String str : picurls){
                ProductPic pic = new ProductPic();
                pic.setPicurl(str);
                pic.setProductId(dto.getId());
                productPics.add(pic);
            }
        }
        //进行存储
        if (CollectionUtils.isNotEmpty(productPics)){
            productPicMapper.add(productPics);
        }
        return 1;
    }

    public PageInfo<ProductVO> queryByPage(ProductDTO dto) {
        //分页插件
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        //查询商品
        List<ProductVO> productVOS = productMapper.queryByPage(dto);
        //处理其他信息
        dealWithVOs(productVOS);
        //返回分页信息
        PageInfo<ProductVO> pageInfo = new PageInfo<ProductVO>(productVOS);
        return pageInfo;
    }

    public ProductVO queryById(ProductDTO dto) {
        return productMapper.queryById(dto);
    }

    private void dealWithVOs(List<ProductVO> productVOS) {
        if (CollectionUtils.isEmpty(productVOS)){
            return;
        }
        //获取所有商品ID集合——》构造查询对象
        List<Long> ids = CommonUtil.getValueList(productVOS, "id");
        ProductPic pic = new ProductPic();
        pic.setIds(ids);
        //批量查询关联关系
        List<ProductPic> productPics = productPicMapper.queryByCondition(pic);
        Map<Long, List<String>> idPicMap = CommonUtil.listforListMap(productPics, "productId", "picurl");
        //组装信息
        for (ProductVO productVO :productVOS){
            List<String> strings = idPicMap.get(productVO.getId());
            productVO.setPicurls(strings);
        }
    }

    public int delete(ProductDTO dto) {
        return productMapper.delete(dto);
    }

    @Transactional
    public int update(ProductDTO dto) {
        //如果商品图片换了
        List<String> picurls = dto.getPicurls();
        if (CollectionUtils.isNotEmpty(picurls)){
            //先删除
            ProductPic pic = new ProductPic();
            pic.setProductId(dto.getId());
            productPicMapper.delete(pic);

            //重新构建并写入
            List<ProductPic> productPics = Lists.newArrayList();
            for (String str :picurls){
                ProductPic productPic = new ProductPic();
                productPic.setProductId(dto.getId());
                productPic.setPicurl(str);
                productPics.add(productPic);
            }
            productPicMapper.add(productPics);
        }
        return productMapper.update(dto);
    }
}
