package com.example.serviceImpl;

import com.example.entity.Student;
import com.example.repository.StudentRepo;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> getAllStudent() {
        System.out.println(studentRepo.findAll());
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> getStudentById(int studentId) {
            return studentRepo.findById(studentId);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public void deleteStudentById(int studentId) {
        studentRepo.deleteById(studentId);
    }
}
