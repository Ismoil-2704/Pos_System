package com.example.postsystemforfather.service.Impl;

import com.example.postsystemforfather.entity.Sealed;
import com.example.postsystemforfather.entity.SealedProducts;
import com.example.postsystemforfather.model.ResponseModel;
import com.example.postsystemforfather.model.SealedProdModel;
import com.example.postsystemforfather.model.SealedProdModelForList;
import com.example.postsystemforfather.repository.SealedProductsRepo;
import com.example.postsystemforfather.service.SealedProductsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SealedProductsServiceImpl implements SealedProductsService {
    private final SealedProductsRepo sealedProductsRepo;

    public SealedProductsServiceImpl(SealedProductsRepo sealedProductsRepo) {
        this.sealedProductsRepo = sealedProductsRepo;
    }

    @Override
    public ResponseModel sealed(SealedProdModel sealedProdModel){
        ResponseModel responseModel = null;
        try {
            List<SealedProducts> sealedProductsList = new LinkedList<>();
            Sealed sealedProd = new Sealed();
            sealedProd.setClient(sealedProdModel.getClient());
            sealedProd.setGave_money(sealedProd.getGave_money());
            sealedProd.setRemains_money(sealedProd.getRemains_money());
            sealedProd.setOn_credit(sealedProdModel.getOn_credit());
            for (SealedProdModelForList product : sealedProdModel.getSealedProds()) {
                SealedProducts sealedProducts = new SealedProducts();
                sealedProducts.setPrice(product.getPrice());
                sealedProducts.setProd_name(product.getName());
                sealedProducts.setCount(product.getCount());
                sealedProductsList.add(sealedProducts);
            }
            sealedProd.setSealedProducts(sealedProductsList);
            sealedProductsRepo.save(sealedProd);
            responseModel = new ResponseModel("PRODUCT_SUCCESSFULLY_SEALED",200);
        }catch (Exception e){
            System.out.println(e.getMessage());
            responseModel = new ResponseModel("PRODUCTS_CAN_NOT_SEALED",500);
        }
        return responseModel;
    }
}
