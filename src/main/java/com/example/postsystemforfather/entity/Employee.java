package com.example.postsystemforfather.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Employee extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "pnfl")
    private String pnfl;
    @Column(name = "passport_number")
    private String passport_number;
}
