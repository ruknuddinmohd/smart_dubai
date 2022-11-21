package com.smart.dubai.book.service.repository;

import com.smart.dubai.book.service.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
    Promo findByCode(String promoCheck);
}
