package com.example.safaricomtalenthack.repository;

import com.example.safaricomtalenthack.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <UserModel,Long>{

    @Query(value = "SELECT * FROM users WHERE login=?",nativeQuery = true)
    UserModel loadByUsername(String username);
}
