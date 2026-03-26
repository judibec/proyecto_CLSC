package com.financialLab.financedevapp.repository;

import com.financialLab.financedevapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.*;

import java.util.Optional;


@Repository
public interface UserRepository extends SimpleCrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    Optional<User> test(String username, String password);
}
