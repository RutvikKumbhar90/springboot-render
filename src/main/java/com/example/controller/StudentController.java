package com.example.controller;

import com.example.dto.StudentDto;
import com.example.entity.Student;
import com.example.response.Response;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/newStudents")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<Response<Student>> getAllProducts(@RequestParam(defaultValue = "0") int skip,
                                                            @RequestParam(defaultValue = "30") int limit) {

        List<Student> allStudents = studentService.getAllStudent();

        int fromIndex = Math.min(skip, allStudents.size());
        int toIndex = Math.min(limit + skip, allStudents.size());

        List<Student> structuredList = allStudents.subList(fromIndex, toIndex);

        Response<Student> response = new Response<>();

        response.setAllStudents(structuredList);
        response.setLimit(limit);
        response.setSkip(skip);
        response.setTotal(allStudents.size());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getProductById(@PathVariable int studentId) {

        Optional<Student> existingStudent = studentService.getStudentById(studentId);

        if (existingStudent.isEmpty()) {
            return new ResponseEntity<>("Student is not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(existingStudent.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Student> saveProduct(@RequestBody StudentDto studentDto) {
        Student student = new Student();

        student.setStudentName(studentDto.getStudentName());
        student.setStudentEmail(studentDto.getStudentEmail());
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateProduct(@RequestBody StudentDto studentDto, @PathVariable int studentId) {

        Optional<Student> existingStudent = studentService.getStudentById(studentId);

        if (existingStudent.isEmpty()) {
            return new ResponseEntity<>("Student is not found", HttpStatus.NOT_FOUND);
        }

        Student student = existingStudent.get();

        student.setStudentName(studentDto.getStudentName());
        student.setStudentEmail(studentDto.getStudentEmail());

        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteProductById(@PathVariable int studentId) {

        Optional<Student> existingStudent = studentService.getStudentById(studentId);

        if (existingStudent.isEmpty()) {
            return new ResponseEntity<>("Student is not found", HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
