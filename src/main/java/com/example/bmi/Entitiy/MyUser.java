package com.example.bmi.Entitiy;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class MyUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    @Column(name = "gender")
    private String gender;
    private Double weight;
    private Double height;

    private LocalDate date;
    private Double indexResult;
    private String indexRange;


}
