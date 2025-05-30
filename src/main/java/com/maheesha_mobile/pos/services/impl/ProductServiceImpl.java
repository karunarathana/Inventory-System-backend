package com.maheesha_mobile.pos.services.impl;

import com.maheesha_mobile.pos.dto.ProductDto;
import com.maheesha_mobile.pos.model.ProductEntity;
import com.maheesha_mobile.pos.model.UserEntity;
import com.maheesha_mobile.pos.repo.ProductRepo;
import com.maheesha_mobile.pos.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        logger.info("Method Executing Start In addNewProduct |productDto={}",productDto);
        ProductEntity savedResponse = productRepo.save(convertProductToProductEntity(0,productDto,"Create"));
        if(savedResponse != null && savedResponse.getProductID() != null){
           logger.info("Product saved successfully: " + savedResponse.getProductID());
           return "Product saved successfully: " + savedResponse.getProductID();
        }
        logger.info("Method Executing Completed In addNewProduct savedResponse={}",savedResponse);
        return "Failed to save product: " + savedResponse.getProductID();
    }
    private ProductEntity convertProductToProductEntity(int updateId,ProductDto productDto,String methodName){
        logger.info("Method Executing Start In convertProductToProductEntity |productDto={}",productDto);
        ProductEntity productEntity = new ProductEntity();
        if(methodName.equals("Create")){
            productEntity.setProductName("Samsung Galaxy A54");
            productEntity.setProductDescription("Latest Samsung mid-range phone with AMOLED display");
            productEntity.setProductPrice("120000");
            productEntity.setDiscountPrice("10000");
            productEntity.setLastPrice("110000"); // productPrice - discountPrice
            productEntity.setBrandName("Samsung");
            productEntity.setProductCreateData(new Date(System.currentTimeMillis()));
            productEntity.setProductCategory("Smartphone");
            productEntity.setProductColor("Black");
            productEntity.setOthers("Includes fast charger and case");
            productEntity.setProductStock("15");
            productEntity.setWarrantyPeriod("1 year");
        } else if (methodName.equals("Update")) {
            productEntity.setProductID(updateId);
            productEntity.setProductName("Samsung Galaxy A54");
            productEntity.setProductDescription("Latest Samsung mid-range phone with AMOLED display");
            productEntity.setProductPrice("120000");
            productEntity.setDiscountPrice("10000");
            productEntity.setLastPrice("110000"); // productPrice - discountPrice
            productEntity.setBrandName("Samsung");
            productEntity.setProductCreateData(new Date(System.currentTimeMillis()));
            productEntity.setProductCategory("Smartphone");
            productEntity.setProductColor("Black");
            productEntity.setOthers("Includes fast charger and case");
            productEntity.setProductStock("15");
            productEntity.setWarrantyPeriod("1 year");
        }
        else{
            logger.info("Invalid method name");
        }

        logger.info("Method Executing Completed In convertProductToProductEntity |productEntity={}",productEntity);
        return productEntity;

    }

    @Override
    public String updateProductById(int id,ProductDto productDto) {
        logger.info("Method Executing Start In updateProductById |id={} |productDto={}",id,productDto);
        ProductEntity productEntity = convertProductToProductEntity(id, productDto, "Update");
        ProductEntity updateProduct = productRepo.save(productEntity);
        logger.info("Method Executing Completed In updateProductById |response={}",updateProduct);
        return "Update Successfully";
    }

    @Override
    public String deleteProductById(int id) {
        logger.info("Method Executing Start In deleteProductById |id={}",id);
        productRepo.deleteById(id);
        logger.info("Method Executing Completed In deleteProductById |response={}","Delete Successfully");
        return "Delete Successfully";
    }

    @Override
    public List<ProductEntity> viewAllProduct() {
        logger.info("Method Executing Start In viewAllProduct");
        List<ProductEntity> all = productRepo.findAll();
        logger.info("Method Executing Start In deleteProductById |response={}","Delete Successfully");
        return all;
    }

    @Override
    public ProductEntity viewSingleProductById(int id) {
        logger.info("Method Executing Start In viewSingleProductById id={}",id);
        Optional<ProductEntity> byproductId = productRepo.findByproductId(id);
        logger.info("Method Executing Completed In viewSingleProductById product={}",byproductId.get());
        return byproductId.get();
    }
}
