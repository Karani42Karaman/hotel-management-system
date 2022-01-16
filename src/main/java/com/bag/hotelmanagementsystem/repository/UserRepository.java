package com.bag.hotelmanagementsystem.repository;

import com.bag.hotelmanagementsystem.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query(value = "SELECT * FROM users u where u.email = ? and u.tcNumber = ?;", nativeQuery = true)
    public UserModel findByUsername(String email, Long tcNumber);

}
