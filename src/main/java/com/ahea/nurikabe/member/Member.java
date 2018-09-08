package com.ahea.nurikabe.member;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private String address;
}
