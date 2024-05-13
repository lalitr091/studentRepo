package com.test.restapi.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.restapi.entity.Student;

/**
* Repository interface for Student entity.
*/
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    public List<Student> findByName(String name);

    public List<Student> findByStudentClass(String studentClass);
    List<Student> findByNameAndStudentClass(String name,String studentClass);

}


