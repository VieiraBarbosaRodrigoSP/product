package br.com.project.product.service.impl;

import br.com.project.product.dto.ProductDTO;
import br.com.project.product.handler.ResourceNotFoundException;
import br.com.project.product.model.Product;
import br.com.project.product.repository.ProductRepository;
import br.com.project.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.project.product.constant.CommonConstant.logConstant.COMMA_WITH_ID;
import static br.com.project.product.constant.CommonConstant.logConstant.NOT_FOUND_BY_ID;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public ProductDTO getProductById(Long productId) {
    var product = productRepository.findById(productId);
    if(product.isEmpty()) throw new ResourceNotFoundException(NOT_FOUND_BY_ID + COMMA_WITH_ID + productId);
    return ProductDTO.of(product.get());
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    List<Product> customers = productRepository.findAllProducts();
    if(customers.isEmpty()) throw new ResourceNotFoundException("Product list is empty");
    return customers.stream().map(ProductDTO::of).collect(Collectors.toList());
  }
}