package com.example.postsystemforfather.service;

import com.example.postsystemforfather.model.ProductsModel;
import com.example.postsystemforfather.model.ResponseModel;

import java.util.List;

public interface ProductService {
    ResponseModel createOrUpdate(List<ProductsModel> productsModel);
}
