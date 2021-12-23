package com.shopping.shopping_project.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.shopping_project.data.CategoryVO;
import com.shopping.shopping_project.data.ProductHistoryVO;
import com.shopping.shopping_project.data.ProductVO;
import com.shopping.shopping_project.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        ProductHistoryVO history = new ProductHistoryVO();
        history.setPih_type("new");
        history.setPih_content(data.makeHistoryStr());
        Integer recent_seq = mapper.getRecentAddedProductSeq();
        history.setPih_pi_seq(recent_seq);

        mapper.insertProductHistory(history);

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

    public ResponseEntity <Map<String, Object>> deleteProduct(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Integer cnt = mapper.isExistProduct(seq);
        if(cnt == 0){
            resultMap.put("status", false);
            resultMap.put("message", "삭제에 실패하였습니다. (존재하지 않는 상품 번호)");
            return new ResponseEntity <Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        else {
            mapper.deleteProduct(seq);
            resultMap.put("status", true);
            resultMap.put("message", "상품이 삭제되었습니다.");

            ProductHistoryVO history = new ProductHistoryVO();
            history.setPih_type("delete");
            history.setPih_pi_seq(seq);
            mapper.insertProductHistory(history);
            return new ResponseEntity <Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
    }

    public Map<String, Object> updateProductInfo(ProductVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateProduct(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        ProductHistoryVO history = new ProductHistoryVO();
        history.setPih_type("modify");
        history.setPih_content(data.makeHistoryStr());
        history.setPih_pi_seq(data.getPi_ci_seq());
        mapper.insertProductHistory(history);

        return resultMap;
    }

    public Map<String, Object> getProductInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getProductInfoBySeq(seq));
        return resultMap;
    }

    public List<CategoryVO> getCategoryName(String keyword) {
        if(keyword == null) keyword = "%%";
        else keyword = "%"+keyword+"%";
        return mapper.getCategoryName(keyword);
    }
}
