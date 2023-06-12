package com.example.postsystemforfather.service.Impl;

import com.example.postsystemforfather.entity.ProductsPurchase;
import com.example.postsystemforfather.entity.Products;
import com.example.postsystemforfather.model.PurchaseProdModel;
import com.example.postsystemforfather.model.ResponseModel;
import com.example.postsystemforfather.repository.PurchaseProdRepo;
import com.example.postsystemforfather.repository.ProductsRepo;
import com.example.postsystemforfather.service.PurchaseProductsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPurchaseServiceImpl implements PurchaseProductsService {
    final
    PurchaseProdRepo comingProductRepo;

    final ProductsRepo productsRepo;

    public ProductPurchaseServiceImpl(PurchaseProdRepo comingProductRepo, ProductsRepo productsRepo) {
        this.comingProductRepo = comingProductRepo;
        this.productsRepo = productsRepo;
    }

    @Override
    public ResponseModel saveOrUpdate(List<PurchaseProdModel> purchaseProdModel) {
        LinkedList<ProductsPurchase> list = new LinkedList<>();
        for (PurchaseProdModel prodModel : purchaseProdModel) {
            ProductsPurchase productsPurchase = new ProductsPurchase();

                Optional<Products> prod = productsRepo.findByProdName(prodModel.getName());
                if (prod.isPresent()) {
                    productsPurchase.setProducts(prod.get());
                    productsPurchase.setCount(prodModel.getCount());
                    productsPurchase.setPrice(prodModel.getPrice());
                    productsPurchase.setCome_date(new Date());
                } else {
                    return new ResponseModel("Mahsulot topilmadi,Mahsulot kiritilganligini tekshiring!", 400);
                }

            list.add(productsPurchase);
        }
        comingProductRepo.saveAll(list);
        return new ResponseModel("Mahsulotlar Muvaffaqiyatli Saqlandi", 200);

    }

    @Override
    public ResponseModel checkedProduct(PurchaseProdModel purchaseProdModel) {
        Optional<ProductsPurchase> byId = comingProductRepo.findById(purchaseProdModel.getId());
        ResponseModel responseModel = null;
        if (byId.isPresent()) {
            ProductsPurchase productsPurchase = byId.orElseThrow();
            productsPurchase.setChecked(true);
            comingProductRepo.save(productsPurchase);
            responseModel = new ResponseModel("PRODUCT_IS_CHECKED", 200);
        } else {
            responseModel = new ResponseModel("PRODUCT_NOT_FOUND", 400);
        }
        return responseModel;
    }
}
