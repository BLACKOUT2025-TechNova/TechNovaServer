package com.techNova.techNovaApplication.user.repository;

import com.techNova.techNovaApplication.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
