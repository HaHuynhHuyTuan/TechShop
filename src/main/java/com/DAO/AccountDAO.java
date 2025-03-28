package com.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Account;


public interface AccountDAO extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
}
