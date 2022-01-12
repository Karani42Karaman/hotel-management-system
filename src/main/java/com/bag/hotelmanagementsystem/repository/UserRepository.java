package com.bag.hotelmanagementsystem.repository;

import com.bag.hotelmanagementsystem.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
