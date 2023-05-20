package com.darshan.controller;

import com.darshan.request.CreateStudentRequest;
import com.darshan.response.StudentResponse;
import com.darshan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest){
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping("/getStudent/{id}")
    public StudentResponse getStudentById(@PathVariable long id){
    return studentService.getStudentById(id);
    }
}
