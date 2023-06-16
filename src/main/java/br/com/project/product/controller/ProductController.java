package br.com.project.product.controller;

import br.com.project.product.dto.ProductDTO;
import br.com.project.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductDTO getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductDTO> getAllProducts() {
    return productService.getAllProducts();
  }
}