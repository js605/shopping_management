package com.shopping.shopping_project.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.shopping_project.data.ProductVO;
import com.shopping.shopping_project.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired ProductMapper mapper;
    public Map<String, Object> addProductInfo(ProductVO data) throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(data.getPi_name().equals("") || data.getPi_name() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "name");
            resultMap.put("message", "상품명을 입력해주세요.");
            return resultMap;
        }
        if(data.getPi_sub().equals("") || data.getPi_sub() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "sub");
            resultMap.put("message", "상품 설명을 입력해주세요.");
            return resultMap;
        }
        if(data.getPi_price().equals("") || data.getPi_price() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "price");
            resultMap.put("message", "가격을 입력해주세요.");
            return resultMap;
        }
        mapper.addProductInfo(data);

        resultMap.put("status", true);
        resultMap.put("message", "상품이 추가되었습니다.");

        return resultMap;
    }
    public Map<String, Object> getProductList(String type, String keyword, Integer offset) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(keyword == null) {
            resultMap.put("keyword", keyword);
            keyword = "%%";
        }
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }

        resultMap.put("type", type);

        if(offset == null) offset = 0;
        List<ProductVO> list = mapper.getProductList(type, keyword, offset);
        Integer cnt = mapper.getProductCnt(type, keyword);

        Integer page = cnt / 10;
        if(cnt % 10 > 0) page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

        return resultMap;
    }

    public Map<String, Object> deleteProduct(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteProduct(seq);
        resultMap.put("status", true);
        resultMap.put("message", "상품이 삭제되었습니다.");

        return resultMap;
    }

    public Map<String, Object> updateProductInfo(ProductVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateProduct(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        return resultMap;
    }

    public Map<String, Object> getProductInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getProductInfoBySeq(seq));
        return resultMap;
    }
}
