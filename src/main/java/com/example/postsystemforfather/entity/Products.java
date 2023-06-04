package com.example.postsystemforfather.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products extends BaseEntity{
    @Column(name = "prod_name")
    private String prod_name;
    @Column(name = "come_price")
    private Double come_price;
    @Column(name = "sel_price")
    private Double sel_price;
    @Column(name = "created_date")
    private Timestamp created_date;
    @Column(name = "last_modifide")
    private Timestamp last_modified;
    @Column(name = "user")
    private Long user_id;
}
