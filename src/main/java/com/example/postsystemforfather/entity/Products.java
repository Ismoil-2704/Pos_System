package com.example.postsystemforfather.entity;

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
public class Products extends BaseEntity{
    @Column(name = "prod_name",unique = true)
    private String prod_name;
    @Column(name = "created_date")
    private Timestamp created_date;
    @Column(name = "last_modifide")
    private Timestamp last_modified;
    @Column(name = "users")
    private Long user_id;

}
