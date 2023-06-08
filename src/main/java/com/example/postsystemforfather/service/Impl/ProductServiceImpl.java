package com.example.postsystemforfather.service.Impl;

import com.example.postsystemforfather.entity.Products;
import com.example.postsystemforfather.model.ProductsModel;
import com.example.postsystemforfather.model.ResponseModel;
import com.example.postsystemforfather.repository.ProductsRepo;
import com.example.postsystemforfather.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductsRepo productsRepo;

    public ProductServiceImpl(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    @Override
    public ResponseModel createOrUpdate(List<ProductsModel> productsModel){
        ResponseModel responseModel = null;
        LinkedList<Products> list = new LinkedList<>();
        try{
            for (ProductsModel model : productsModel) {
                Products products = new Products();
                if (model.getId() != null){
                    Optional<Products> byId = productsRepo.findById(model.getId());
                    if (byId.isPresent()) products = byId.get();
                }
                products.setProd_name(model.getName());
                list.add(products);
            }
            productsRepo.saveAll(list);
            responseModel = new ResponseModel("PRODUCT_SUCCESSFULLY_SAVED",200);
        }catch (Exception e){
            System.out.println(e.getMessage());
            responseModel = new ResponseModel("ERROR_WHEN_SAVE_PRODUCT",500);
        }
        return responseModel;
    }
}
