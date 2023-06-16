package br.com.project.product.dto;

import br.com.project.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opengamma.strata.collect.ArgChecker;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@JsonIgnoreProperties
public class ProductDTO implements Serializable {
  private Long id;
  private String name;
  private String description;
  private double price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Product toEntity() {
    Product product = new Product();
    BeanUtils.copyProperties(this, product);
    return product;
  }

  public static ProductDTO of(Product product) {
    ArgChecker.notNull(product, "customer");
    ProductDTO dto = new ProductDTO();
    BeanUtils.copyProperties(product, dto);
    return dto;
  }
}