package com.example.postsystemforfather.service.Impl;

import com.example.postsystemforfather.entity.Products;
import com.example.postsystemforfather.model.ProductsModel;
import com.example.postsystemforfather.model.ResponseModel;
import com.example.postsystemforfather.repository.ProductsRepo;
import com.example.postsystemforfather.service.ProductService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
                Optional<Products> byId = productsRepo.findByProdName(model.getName());
                if (byId.isPresent()){
                     products = byId.get();
                     products.setLast_modified(new Timestamp(new Date().getTime()));
                }else {
                    products.setCreated_date(new Timestamp(new Date().getTime()));
                }
                products.setProdName(model.getName());
                list.add(products);
            }
            productsRepo.saveAll(list);
            responseModel = new ResponseModel("Mahsulot muvaffaqiyatli kiritildi!",200);
        }catch (Exception e){
            System.out.println(e.getMessage());
            responseModel = new ResponseModel("Mahsulot kitritishda xatolik!",500);
        }
        return responseModel;
    }
}
