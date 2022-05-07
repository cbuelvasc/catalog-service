package com.inditex.catalogservice.ports.output.dao.jpa.repository;

import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p where ?1 between p.startDate and p.endDate and p.productId = ?2 and p.brandId = ?3")
    List<Price> findByDatesAndProductIdAndBrandId(LocalDateTime applicationDateTime, Long productId, Long brandId);

}
