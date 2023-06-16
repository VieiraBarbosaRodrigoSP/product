package br.com.project.product.service.impl;

import br.com.project.product.BaseMocks;
import br.com.project.product.handler.ResourceNotFoundException;
import br.com.project.product.model.Product;
import br.com.project.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static br.com.project.product.constant.CommonConstant.baseMocksConstant.*;
import static br.com.project.product.constant.CommonConstant.logConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
  @InjectMocks
  private ProductServiceImpl productServiceImpl;
  @Mock
  private ProductRepository productRepository;

  @Test
  void should_getProduct_ok() {
    var product = BaseMocks.getProduct(PRODUCT_NAME_01, PRODUCT_DESCRIPTION_01, PRODUCT_PRICE_01, 1l).get();
    when(productRepository.findById(any())).thenReturn(Optional.of(product));
    var actual = productServiceImpl.getProductById(1l);
    assertEquals(product.getName(), actual.getName());
  }

  @Test
  void should_getProduct_ResourceNotFoundException() {
    var productId = 1l;
    when(productRepository.findById(any())).thenReturn(Optional.empty());
    var thrown = assertThrows( ResourceNotFoundException.class, () -> productServiceImpl.getProductById(productId) );
    assertTrue(thrown.getLocalizedMessage().contentEquals(NOT_FOUND_BY_ID+ COMMA_WITH_ID + productId));
  }

  @Test
  void should_getAllproducts_isOK() {
    List<Product> products = BaseMocks.getAllProducts();
    when(productRepository.findAllProducts()).thenReturn(products);
    var actual = productServiceImpl.getAllProducts();
  }

  @Test
  void should_getAllproducts_() {
    when(productRepository.findAllProducts()).thenReturn(List.of());
    var thrown = assertThrows( ResourceNotFoundException.class, () -> productServiceImpl.getAllProducts() );
    assertTrue(thrown.getLocalizedMessage().contentEquals(LIST_IS_EMPTY));
  }
}