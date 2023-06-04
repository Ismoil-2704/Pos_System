package com.example.postsystemforfather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsModel {
    private Long id;
    private String name;
    private Double come_price;
    private Double sel_price;
}
