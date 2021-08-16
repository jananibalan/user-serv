package com.janani.user.repository;

import com.janani.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    static void deleteById(int id) {
    }

    User findByUserId(Long userId);
}
