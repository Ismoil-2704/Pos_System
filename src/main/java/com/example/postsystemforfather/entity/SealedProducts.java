package com.example.postsystemforfather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SealedProducts extends BaseEntity{
    @Column(name = "prod_name")
    private String prod_name;
    @Column(name = "count")
    private Integer count;
    @Column(name = "price")
    private Integer price;
}
