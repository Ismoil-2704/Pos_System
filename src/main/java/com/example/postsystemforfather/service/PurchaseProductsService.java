package com.example.postsystemforfather.service;

import com.example.postsystemforfather.model.PurchaseProdModel;
import com.example.postsystemforfather.model.ResponseModel;

import java.util.List;

public interface PurchaseProductsService {
    ResponseModel saveOrUpdate(List<PurchaseProdModel> purchaseProdModel);
    ResponseModel checkedProduct(PurchaseProdModel purchaseProdModel);
}
