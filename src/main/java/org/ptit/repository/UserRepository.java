package org.ptit.repository;

import liquibase.pro.packaged.P;
import org.ptit.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findUserByAge(Pageable pageable, int age);
    @Query(value = "SELECT * FROM users u WHERE u.name = :name  or u.name LIKE '%:name%'", nativeQuery = true)
    List<User> findUserByName(String name);


}
