package com.example.postsystemforfather.repository;

import com.example.postsystemforfather.entity.ComingProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComingProdRepo extends JpaRepository<ComingProducts,Long> {
}
