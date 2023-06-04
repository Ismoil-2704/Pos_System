package com.example.postsystemforfather.repository;

import com.example.postsystemforfather.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Long> {
}
