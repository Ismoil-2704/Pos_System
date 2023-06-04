package com.example.postsystemforfather.service;

import com.example.postsystemforfather.model.ProductsModel;
import com.example.postsystemforfather.model.ResponseModel;

public interface ProductService {
    ResponseModel createOrUpdate(ProductsModel productsModel);
}
