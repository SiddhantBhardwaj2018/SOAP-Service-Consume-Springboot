package com.siddhantbhardwaj.productSoapConsumeService.service;

import com.siddhantbhardwaj.productSoapConsumeService.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductPort productPort;

    @Autowired
    public ProductService(ProductPort productPort){
        this.productPort=productPort;
    }

    public GetProductResponse getProductResponse(String name){
        GetProductRequest getProductRequest = new GetProductRequest();
        getProductRequest.setName(name);
        return productPort.getProduct(getProductRequest);
    }

    public GetProductsResponse getProductsResponse(){
        GetProductsRequest getProductsRequest = new GetProductsRequest();
        return productPort.getProducts(getProductsRequest);
    }

    public PostProductResponse postProductResponse(String name, double price, String description){
        PostProductRequest postProductRequest = new PostProductRequest();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        postProductRequest.setProduct(product);
        return productPort.postProduct(postProductRequest);
    }

}
