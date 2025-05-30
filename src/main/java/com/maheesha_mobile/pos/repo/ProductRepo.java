package com.maheesha_mobile.pos.repo;

import com.maheesha_mobile.pos.model.ProductEntity;
import com.maheesha_mobile.pos.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,Integer> {
    @Query(value = "SELECT * FROM t_product WHERE productid = :productId", nativeQuery = true)
    Optional<ProductEntity> findByproductId(int productId);
}
