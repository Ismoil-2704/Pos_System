package com.example.postsystemforfather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComingProdModel {
    private Long Id;
    private String name;
    private Integer count;
    private Integer price;
}
