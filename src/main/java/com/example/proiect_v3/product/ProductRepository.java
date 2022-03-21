package com.example.proiect_v3.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT i FROM Product i WHERE i.id = ?1")
    public Product findById(long id);

}
