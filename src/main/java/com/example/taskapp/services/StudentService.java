package com.example.taskapp.services;

import com.example.taskapp.models.Student;
import com.example.taskapp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MailService mailService;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) throws IOException {
        // Add business logic here if needed
//        Map<String, String> emailInfo = new HashMap<>();
//        emailInfo.put("contentType", "text/plain");
//        emailInfo.put("content", "Your account has been created successfully. Please visit out website and go through the feed to grab the exclusive oppurtunities. ");
//        emailInfo.put("toMail", student.getEmail());
//        emailInfo.put("subject", "Account created");
//        mailService.sendMail(emailInfo);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setEmail(studentDetails.getEmail());
            student.setAge(studentDetails.getAge());
            return studentRepository.save(student);
        } else {
            // Handle not found scenario
            return null;
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

