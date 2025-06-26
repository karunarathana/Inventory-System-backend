package com.maheesha_mobile.pos.services;

import com.maheesha_mobile.pos.dto.ProductDto;
import com.maheesha_mobile.pos.model.ProductEntity;

import java.util.List;

public interface ProductService {
    String addNewProduct(ProductDto productDto);
    String updateProductById(int id,ProductDto productDto);
    String deleteProductById(int id);
    List<ProductEntity> viewAllProduct();
    ProductEntity viewSingleProductById(int id);
    List<ProductEntity> viewAllProductByCategoryName(String categoryName);
}
