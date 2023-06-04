package com.example.postsystemforfather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SealedProdModel {
    private String client;
    private Double gaveMoney;
    private Double remainsMoney;
    private Boolean on_credit;
    private List<SealedProdModelForList> sealedProds;
}
