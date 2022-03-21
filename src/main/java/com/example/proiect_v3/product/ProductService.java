package com.example.proiect_v3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(long id) {
        return repo.findById(id);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

}
