package com.example.service;

import com.example.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<Student> getAllStudent();

    public Optional<Student> getStudentById(int studentId);

    public Student saveStudent(Student student);

    public void deleteStudentById(int studentId);
}
