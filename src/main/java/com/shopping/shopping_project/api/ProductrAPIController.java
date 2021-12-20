package com.shopping.shopping_project.api;

import java.util.Map;

import com.shopping.shopping_project.data.ProductVO;
import com.shopping.shopping_project.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductrAPIController {
    @Autowired ProductService service;
    @PostMapping("/product/add")
    public Map<String, Object> postProductAdd(@RequestBody ProductVO data) throws Exception{
        return service.addProductInfo(data);
    }

    @DeleteMapping("/product/delete")
    public Map<String, Object> deleteProduct(@RequestParam Integer seq) {
        return service.deleteProduct(seq);
    }

    @PatchMapping("/product/update")
    public Map<String, Object> updateProduct(@RequestBody ProductVO data) {
        return service.updateProductInfo(data);
    }

    @GetMapping("/product/get")
    public Map<String, Object> getProductInfoBySeq(@RequestParam Integer seq) {
        return service.getProductInfoBySeq(seq);
    }
}