package com.smart.dubai.book.service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String code;
    private Double fictionPercentage;
    private Double comicPercentage;
}
