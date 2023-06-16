package br.com.project.product.repository;

import br.com.project.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query(value = "SELECT u FROM Product u")
  List<Product> findAllProducts();
}