package dao;

import domain.ProductPic;

import java.util.List;

public interface ProductPicMapper {
    /**
     * 新增关联关系
     * @param productPics
     * @return
     */
    int add(List<ProductPic> productPics);

    /**
     * 查询关联关系
     * @param productPic
     * @return
     */

    List<ProductPic> queryByCondition(ProductPic productPic);

    /**
     * 删除关联关系
     * @param productPic
     * @return
     */
    int delete(ProductPic productPic);
}
