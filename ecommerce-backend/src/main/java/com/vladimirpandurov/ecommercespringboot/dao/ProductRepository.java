package com.vladimirpandurov.ecommercespringboot.dao;

import com.vladimirpandurov.ecommercespringboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface ProductRepository extends JpaRepository<Product, Long> {

}
