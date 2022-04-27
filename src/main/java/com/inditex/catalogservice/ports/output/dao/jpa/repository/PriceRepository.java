package com.inditex.catalogservice.ports.output.dao.jpa.repository;

import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p where ((p.startDate between ?1 and ?2) and p.endDate >= ?3) and p.productId = ?4 and p.brandId = ?5")
    List<Price> findByDatesAndProductIdAndBrandId(LocalDateTime startApplicationDateTime, LocalDateTime untilApplicationDate, LocalDateTime endApplicationDate, Long productId, Long brandId);


    @Query("select p from Price p where (p.startDate = (select max(p2.startDate) from Price p2 where p2.endDate >= p2.startDate) or p.startDate >= ?1) and p.productId = ?2 and p.brandId = ?3")
    List<Price> findByDateAndProductIdAndBrandId(LocalDateTime startApplicationDateTime, Long productId, Long brandId);
}
