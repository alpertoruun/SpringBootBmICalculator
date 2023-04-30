package com.example.bmi.SpringApp.Controller;

import com.example.bmi.SpringApp.Entitiy.MyUser;
import com.example.bmi.SpringApp.bmi.BmiCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bmi.SpringApp.repo.UserRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

@Autowired
    private UserRepository userRepository;

    @PostMapping
    public MyUser addUser(@RequestBody MyUser user) {
        user.setDate(LocalDate.now());
        double bmi = user.getWeight() / Math.pow(user.getHeight(), 2);
        user.setIndexResult(bmi);
        user.setIndexRange(BmiCategory.findByValue(bmi).toString());
        return userRepository.save(user);
    }

    @GetMapping
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}/bmi")
            public BmiCategory getUserBMI(@PathVariable Long id) {
            MyUser user = userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Invalid user ID: " + id)
    );
            Double bmi = user.getWeight() / Math.pow(user.getHeight(), 2);
            return BmiCategory.findByValue(bmi);
            }
}
