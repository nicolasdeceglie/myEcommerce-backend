package com.nicolas.myEcommerce.repository;

import com.nicolas.myEcommerce.model.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
