package br.com.project.product.service;

import br.com.project.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
  ProductDTO getProductById(Long productId);
  List<ProductDTO> getAllProducts();
}