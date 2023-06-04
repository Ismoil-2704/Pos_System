package com.example.postsystemforfather.repository;

import com.example.postsystemforfather.entity.Sealed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SealedProductsRepo extends JpaRepository<Sealed,Long> {
}
