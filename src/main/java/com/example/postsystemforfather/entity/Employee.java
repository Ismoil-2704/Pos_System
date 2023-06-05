package com.example.postsystemforfather.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "pnfl")
    private String pnfl;
    @Column(name = "passport_number")
    private String passport_number;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
}
