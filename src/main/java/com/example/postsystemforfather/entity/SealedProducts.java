package com.example.postsystemforfather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SealedProducts extends BaseEntity{

    @ManyToOne
    private Products products;
    @Column(name = "count")
    private Integer count;
    @Column(name = "price")
    private Integer price;
}
