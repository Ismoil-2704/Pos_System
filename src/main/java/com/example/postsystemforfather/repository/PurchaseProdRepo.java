package com.example.postsystemforfather.repository;

import com.example.postsystemforfather.entity.ProductsPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProdRepo extends JpaRepository<ProductsPurchase,Long> {
}
