package com.ra.repository;

import com.ra.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    Boolean existsByEmail(String email);
    Page<User> getAllByFullNameContaining(String a, Pageable pageable);
}
