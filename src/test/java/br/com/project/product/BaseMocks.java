package br.com.project.product;

import br.com.project.product.dto.ProductDTO;
import br.com.project.product.model.Product;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Optional;

import static br.com.project.product.constant.CommonConstant.baseMocksConstant.*;

public final class BaseMocks {
  public static ProductDTO getProductDTO(String name, String description, double preco, Long id) {
    ProductDTO product = new ProductDTO();
    product.setName(name);
    product.setDescription(description);
    product.setPrice(preco);
    if(id==null) product.setId(id);
    return product;
  }
  public static List<ProductDTO> getAllProductsDTO(){
    ProductDTO product01 = BaseMocks.getProductDTO(PRODUCT_NAME_01, PRODUCT_DESCRIPTION_01, PRODUCT_PRICE_01, 1l);
    ProductDTO product02 = BaseMocks.getProductDTO(PRODUCT_NAME_02, PRODUCT_DESCRIPTION_02, PRODUCT_PRICE_02, 2l);
    ProductDTO product03 = BaseMocks.getProductDTO(PRODUCT_NAME_03, PRODUCT_DESCRIPTION_03, PRODUCT_PRICE_03, 3l);
    ProductDTO product04 = BaseMocks.getProductDTO(PRODUCT_NAME_04, PRODUCT_DESCRIPTION_04, PRODUCT_PRICE_04, 4l);

    List<ProductDTO> Products = ImmutableList.<ProductDTO>builder()
        .add(product01)
        .add(product02)
        .add(product03)
        .add(product04)
        .build();

    return Products;
  }
  public static Optional<Product> getProduct(String name, String description, Double preco, Long id){
    Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setDescription(description);
    product.setPrice(preco);
    return Optional.of(product);
  }
  public static List<Product> getAllProducts(){
    Product product01 = BaseMocks.getProduct(PRODUCT_NAME_01, PRODUCT_DESCRIPTION_01, PRODUCT_PRICE_01, 1l).get();
    Product product02 = BaseMocks.getProduct(PRODUCT_NAME_02, PRODUCT_DESCRIPTION_02, PRODUCT_PRICE_02, 2l).get();
    Product product03 = BaseMocks.getProduct(PRODUCT_NAME_03, PRODUCT_DESCRIPTION_03, PRODUCT_PRICE_03, 3l).get();
    Product product04 = BaseMocks.getProduct(PRODUCT_NAME_04, PRODUCT_DESCRIPTION_04, PRODUCT_PRICE_04, 4l).get();

    List<Product> Products = ImmutableList.<Product>builder()
        .add(product01)
        .add(product02)
        .add(product03)
        .add(product04)
        .build();

    return Products;
  }
}