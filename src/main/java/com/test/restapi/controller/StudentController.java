package com.test.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.restapi.entity.Student;
import com.test.restapi.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student newStudent = studentService.savStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudentsByName(@RequestParam(name = "name",required = false) String name,
    @RequestParam(name = "class",required = false) String studentClass) {
        if (name != null && studentClass != null) {
            // Return students by both name and class
            List<Student> students = studentService.getStudentByNameAndClass(name, studentClass);
            return ResponseEntity.ok(students);
        } else if (name != null) {
            // Return students by name
            List<Student> students = studentService.getStudentByName(name);
            return ResponseEntity.ok(students);
        } else if (studentClass != null) {
            // Return students by class
            List<Student> students = studentService.getStudentByClass(studentClass);
            return ResponseEntity.ok(students);
        } else {
            // Handle error - either name or class parameter is required
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }
}
