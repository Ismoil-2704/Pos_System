package com.example.postsystemforfather.service.Impl;

import com.example.postsystemforfather.entity.ComingProducts;
import com.example.postsystemforfather.model.ComingProdModel;
import com.example.postsystemforfather.model.ResponseModel;
import com.example.postsystemforfather.repository.ComingProdRepo;
import com.example.postsystemforfather.service.ComingProductsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComingProductServiceImpl implements ComingProductsService {
    final
    ComingProdRepo comingProductRepo;

    public ComingProductServiceImpl(ComingProdRepo comingProductRepo) {
        this.comingProductRepo = comingProductRepo;
    }

    @Override
    public ResponseModel saveOrUpdate(ComingProdModel comingProdModel) {
        ComingProducts comingProducts = new ComingProducts();
        ResponseModel responseModel = null;
        if (comingProdModel.getId() != null) {
            Optional<ComingProducts> byId = comingProductRepo.findById(comingProdModel.getId());
            if (byId.isPresent()) {
                comingProducts = byId.get();
                comingProducts.setProd_name(comingProdModel.getName());
                comingProducts.setCount(comingProdModel.getCount());
                comingProducts.setPrice(comingProdModel.getPrice());
                comingProductRepo.save(comingProducts);
                responseModel = new ResponseModel("PRODUCT_UPDATED", 200);
            } else {
                responseModel = new ResponseModel("PRODUCT_NOT_FOUND", 400);
            }
        } else {
            comingProducts.setProd_name(comingProdModel.getName());
            comingProducts.setCount(comingProdModel.getCount());
            comingProducts.setPrice(comingProdModel.getPrice());
            comingProductRepo.save(comingProducts);
            responseModel = new ResponseModel("PRODUCT_SAVED", 200);
        }
        return responseModel;
    }

    @Override
    public ResponseModel checkedProduct(ComingProdModel comingProdModel) {
        Optional<ComingProducts> byId = comingProductRepo.findById(comingProdModel.getId());
        ResponseModel responseModel = null;
        if (byId.isPresent()) {
            ComingProducts comingProducts = byId.orElseThrow();
            comingProducts.setChecked(true);
            comingProductRepo.save(comingProducts);
            responseModel = new ResponseModel("PRODUCT_IS_CHECKED", 200);
        } else {
            responseModel = new ResponseModel("PRODUCT_NOT_FOUND", 400);
        }
        return responseModel;
    }
}
