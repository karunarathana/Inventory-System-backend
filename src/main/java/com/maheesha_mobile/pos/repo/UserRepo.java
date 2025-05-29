package com.maheesha_mobile.pos.repo;

import com.maheesha_mobile.pos.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Query(value = "SELECT * FROM t_user WHERE email = :userEmail", nativeQuery = true)
    Optional<UserEntity> findByUserEmail(String userEmail);
}
