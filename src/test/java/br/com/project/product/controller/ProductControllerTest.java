package br.com.project.product.controller;

import br.com.project.product.BaseMocks;
import br.com.project.product.dto.ProductDTO;
import br.com.project.product.handler.ResourceNotFoundException;
import br.com.project.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.com.project.product.constant.CommonConstant.baseMocksConstant.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  ProductService productService;

  @BeforeEach
  public void setUp() {
    Mockito.reset(productService);
  }

  @Test
  public void should_ReturnProductById_isOk() throws Exception {
    ProductDTO Product = BaseMocks.getProductDTO(PRODUCT_NAME_01, PRODUCT_DESCRIPTION_01, PRODUCT_PRICE_01, 1l);

    when(productService.getProductById(1L)).thenReturn(Product);

    mockMvc.perform(get("/products/1").accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.name", is(PRODUCT_NAME_01)))
        .andExpect(jsonPath("$.description", is(PRODUCT_DESCRIPTION_01)))
        .andExpect(jsonPath("$.price", is(PRODUCT_PRICE_01)));
  }

  @Test
  public void should_ReturnProductById_isNotFound() throws Exception {
    when(productService.getProductById(1L)).thenThrow(ResourceNotFoundException.class);

    mockMvc.perform(get("/products/1").accept(APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void should_ReturnGetAllProducts_isOk() throws Exception {
    List<ProductDTO> Products = BaseMocks.getAllProductsDTO();

    when(productService.getAllProducts()).thenReturn(Products);

    mockMvc.perform(get("/products/").accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("length()").value(Products.size()))
        .andExpect(jsonPath("$[0].name", is(PRODUCT_NAME_01)))
        .andExpect(jsonPath("$[0].description", is(PRODUCT_DESCRIPTION_01)))
        .andExpect(jsonPath("$[0].price", is(PRODUCT_PRICE_01)))
        .andExpect(jsonPath("$[1].name", is(PRODUCT_NAME_02)))
        .andExpect(jsonPath("$[1].description", is(PRODUCT_DESCRIPTION_02)))
        .andExpect(jsonPath("$[1].price", is(PRODUCT_PRICE_02)))
        .andExpect(jsonPath("$[2].name", is(PRODUCT_NAME_03)))
        .andExpect(jsonPath("$[2].description", is(PRODUCT_DESCRIPTION_03)))
        .andExpect(jsonPath("$[2].price", is(PRODUCT_PRICE_03)));
  }

  @Test
  public void should_ReturnGetAllProducts_isNotFound() throws Exception {
    when(productService.getAllProducts()).thenThrow(ResourceNotFoundException.class);

    mockMvc.perform(get("/products/").accept(APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}