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
@NoArgsConstructor
@AllArgsConstructor
public class Products extends BaseEntity{
    @Column(name = "prod_name",unique = true)
    private String prodName;
    @Column(name = "created_date")
    private Timestamp created_date;
    @Column(name = "last_modifide")
    private Timestamp last_modified;
    @Column(name = "users")
    private Long user_id;

}
