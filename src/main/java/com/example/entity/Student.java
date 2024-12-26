package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "newStudents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String studentName;
    private String studentEmail;

}
