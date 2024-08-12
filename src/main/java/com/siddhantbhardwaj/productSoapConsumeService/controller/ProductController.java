package com.siddhantbhardwaj.productSoapConsumeService.controller;

import com.siddhantbhardwaj.productSoapConsumeService.service.ProductService;
import com.siddhantbhardwaj.productSoapConsumeService.wsdl.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public List<Product> getProducts() throws Exception{
        return productService.getProductsResponse().getProducts();
    }

    @PostMapping("/product")
    public Product getProduct(@RequestBody Map<String,String> productNameMap) throws Exception{
        return  productService.getProductResponse(productNameMap.get("name")).getProduct();
    }

    @PostMapping("/postProduct")
    public Product setProduct(@RequestBody Map<String,Object> productDataMap) throws Exception{
        return productService.postProductResponse(
                (String) productDataMap.get("name"),
                (Double) productDataMap.get("price"),
                (String) productDataMap.get("description")
        ).getProduct();
    }

}
