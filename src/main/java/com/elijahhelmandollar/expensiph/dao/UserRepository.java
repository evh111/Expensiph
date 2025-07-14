package com.elijahhelmandollar.expensiph.dao;

import com.elijahhelmandollar.expensiph.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}