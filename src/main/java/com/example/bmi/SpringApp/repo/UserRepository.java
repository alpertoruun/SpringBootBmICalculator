package com.example.bmi.SpringApp.repo;


import com.example.bmi.SpringApp.Entitiy.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
}
