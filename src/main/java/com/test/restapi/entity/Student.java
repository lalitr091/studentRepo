package com.test.restapi.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "STUDENT")
public class Student {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, name="naem")
    private String name;
 
    @Column(nullable = false, name = "dateofbirth")
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
 
    @Column(nullable = false, name = "joiningdate")
    @JsonProperty("joining_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;
    @Column(nullable = false, name="class")
    @JsonProperty("class")
    private String studentClass;
 
}
