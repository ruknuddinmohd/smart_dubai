package com.smart.dubai.book.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutBean {

    private List<BookBean> books;
    private String promotionCode;
}
