package com.example.postsystemforfather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseProdModel {
    private Long Id;
    private String name;
    private Integer count;
    private Double price;

    public PurchaseProdModel(String name, Integer count, Double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }
}
