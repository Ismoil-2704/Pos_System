package com.example.postsystemforfather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductsPurchase extends BaseEntity{

    @ManyToOne
    private Products products;
    @Column(name = "count")
    private Integer count;
    @Column(name = "price")
    private Double price;
    @Column(name = "come_date")
    private Date come_date;
    @Column(name = "users")
    private Long user_id;
    @Column(name = "checked")
    private Boolean checked;


}
