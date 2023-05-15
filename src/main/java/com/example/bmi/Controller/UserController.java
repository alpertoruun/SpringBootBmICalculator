package com.example.bmi.Controller;

import com.example.bmi.Entitiy.MyUser;
import com.example.bmi.bmi.BmiCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.bmi.repo.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public MyUser addUser(@ModelAttribute MyUser user)  {
        double bmi = user.getWeight() / Math.pow(user.getHeight(), 2);
        user.setDate(LocalDate.now());
        user.setIndexResult(bmi);
        user.setIndexRange(BmiCategory.findByValue(bmi).toString());
        return userRepository.save(user);
    }

    @GetMapping
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/")
    public String showForm(MyUser user) {
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") MyUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        user = addUser(user);
        double bmi = user.getIndexResult();
        String message = "BMI değeri başarıyla hesaplandı: " + bmi;
        
        model.addAttribute("user", user);
        model.addAttribute("message", message);

        return "result";
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
