package com.example.postsystemforfather.service;

import com.example.postsystemforfather.model.ComingProdModel;
import com.example.postsystemforfather.model.ResponseModel;

public interface ComingProductsService {
    ResponseModel saveOrUpdate(ComingProdModel comingProdModel);
    ResponseModel checkedProduct(ComingProdModel comingProdModel);
}
