package com.shopping.shopping_project.mapper;

import java.util.List;

import com.shopping.shopping_project.data.ProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    public void addProductInfo(ProductVO data);

    public List<ProductVO> getProductList(String type, String keyword, Integer offset);

    public Integer getProductCnt(String type, String keyword);

    public void deleteProduct(Integer seq);

    public void updateProduct(ProductVO data);

    public ProductVO getProductInfoBySeq(Integer seq);
}
