package com.test.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.restapi.entity.Student;
import com.test.restapi.repository.StudentRepo;


@Service
public class StudentService {
     private final StudentRepo studentRepo;
 
    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
 

    public Student savStudent(Student student) {
        return studentRepo.save(student);
    }
 

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
 
  //Get details of student by id
    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }


    //Get details of student by name
    public List<Student> getStudentByName(String name) {
        return studentRepo.findByName(name);
    }
    //Get details of student by class
    public List<Student> getStudentByClass(String studentClass) {
        return studentRepo.findByStudentClass(studentClass);
    }

    public List<Student> getStudentByNameAndClass(String name, String studentClass) {
        return studentRepo.findByNameAndStudentClass(name,studentClass);
    }
 
    // update student
    public Student updateStudent(Student updatedStudent) {
        if( updatedStudent.getId()==null){
            return studentRepo.save(updatedStudent);
        } else {
        Optional<Student> existingStudent = studentRepo.findById(updatedStudent.getId());
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(updatedStudent.getName());
            student.setDateOfBirth(updatedStudent.getDateOfBirth());
            student.setJoiningDate(updatedStudent.getJoiningDate());
            student.setStudentClass(updatedStudent.getStudentClass());
            return studentRepo.save(student);
        } else {
            throw new RuntimeException("Student not found with id: "+ updatedStudent.getId());
        }
    }
    }
 
}
