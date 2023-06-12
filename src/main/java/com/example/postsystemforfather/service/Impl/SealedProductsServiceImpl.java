package com.example.postsystemforfather.service.Impl;

import com.example.postsystemforfather.entity.Products;
import com.example.postsystemforfather.entity.Sealed;
import com.example.postsystemforfather.entity.SealedProducts;
import com.example.postsystemforfather.model.ResponseModel;
import com.example.postsystemforfather.model.SealedProdModel;
import com.example.postsystemforfather.model.SealedProdModelForList;
import com.example.postsystemforfather.repository.ProductsRepo;
import com.example.postsystemforfather.repository.SealedProductsRepo;
import com.example.postsystemforfather.service.SealedProductsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SealedProductsServiceImpl implements SealedProductsService {
    private final SealedProductsRepo sealedProductsRepo;
    private final ProductsRepo productsRepo;

    public SealedProductsServiceImpl(SealedProductsRepo sealedProductsRepo, ProductsRepo productsRepo) {
        this.sealedProductsRepo = sealedProductsRepo;
        this.productsRepo = productsRepo;
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
                Optional<Products> prod = productsRepo.findByProdName(product.getName());
                if (prod.isPresent()) {
                    sealedProducts.setProducts(prod.get());
                    sealedProducts.setPrice(product.getPrice());
                    sealedProducts.setCount(product.getCount());
                    sealedProductsList.add(sealedProducts);
                }else {
                    return new ResponseModel("Mahsulot topilmadi,Mahsulot kiritilganligini tekshiring!", 400);
                }
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
