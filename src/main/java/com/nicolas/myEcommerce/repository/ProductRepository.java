package com.nicolas.myEcommerce.repository;

import com.nicolas.myEcommerce.model.product.Category;
import com.nicolas.myEcommerce.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product>findByIsAvailableTrue();
    List<Product> findByIsAvailableFalse();
    Page<Product> findByNameStartingWithIgnoreCase(String startingName, Pageable pageable);
    Optional<Product> findByName(String name);
    Page<Product> findByPriceGreaterThanEqual(Double price, Pageable pageable);
    @Query("select p from Product p where p.price > :startPrice and p.price < :endPrice")
    Page<Product> findByPriceGreaterThanLessThan(@Param("startPrice")Double startPrice,@Param("endPrice") Double endPrice ,Pageable pageable);
    Optional<Category> findCategoryByName(String name);
}
