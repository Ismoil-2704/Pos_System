package com.example.postsystemforfather.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Sealed extends BaseEntity{
    @Column(name = "client_name")
    private String client;
    @Column(name = "seller_id")
    private Long seller;
    @Column(name = "seller_name")
    private String seller_name;
    @Column(name = "sealed_date")
    private Timestamp sealed_date;
    @Column(name = "on_credit")
    private Boolean on_credit;
    @Column(name = "gave_mony")
    private Double gave_money;
    @Column(name = "remains_money")
    private Double remains_money;
    @OneToMany
    @Column(name = "prducts")
    private List<SealedProducts> sealedProducts;
}
