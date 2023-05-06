package com.example.bmi.repo;


import com.example.bmi.Entitiy.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
}
