package com.demo.example.student_library_management_system.controller;

import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import com.demo.example.student_library_management_system.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student/apis")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Responseentity - takes the response from APIs and binds it to responseentity and sends it
    // HTTP codes
    // 200 -ok sucess
//    201-created
//    404-not found
//    400-bad request
//    500-internal server error
//    401-unauthorized

    /*
    Loggers - displays the message in console which helps in keeping track of the application flow
    levels of loggers
    info - displays normal information
    warn - to give warning message
    debug - while running in debug mode
    error - to give error or exceptions messages
     */

    // declaring the loggers
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        logger.info("saveStudent API started");
        try {
            String response = studentService.addStudent(studentRequestDto);
            logger.info("saveStudent API ended");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            logger.error("Exception occured : "+e.getMessage());
            return ResponseEntity.internalServerError().body("Student could not be saved : "+e.getMessage());
        }
    }

    @GetMapping("/find/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable int studentId){
        logger.info("getStudentById API started");
        try {
            Student student = studentService.getStudentById(studentId);
            logger.info("getStudentById API ended");
            return ResponseEntity.ok(student);
        }catch (Exception e){
            logger.error("Exception occured : "+e.getMessage());
            return ResponseEntity.internalServerError().body("Student with id "+studentId+" is not found"+e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/findByPage")
    public List<Student> getStudentsByPage(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortInput){
        List<Student> studentList = studentService.getStudentsBasedOnPage(pageNo,pageSize, sortInput);
        return studentList;
    }

    @GetMapping("/count")
    public String countStudents(){
        long count = studentService.countStudents();
        return "Total students present in database are :"+count;
    }

    @DeleteMapping("/delete/{studentId}")
    public String deleteStudentById(@PathVariable int studentId){
        String response = studentService.deleteStudentById(studentId);
        return response;
    }

    @PutMapping("/update/{studentId}")
    public String updateStudent(@PathVariable int studentId, @RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.updateStudent(studentId,studentRequestDto);
        return response;
    }

    @GetMapping("/findByDept")
    public List<Student> getStudentByDepartment(@RequestParam String department){
        List<Student> studentList = studentService.getStudentByDepartment(department);
        return studentList;
    }

    @GetMapping("/findBySem")
    public List<Student> getStudentBySem(@RequestParam String sem){
        List<Student> studentList = studentService.getStudentBySem(sem);
        return studentList;
    }
}
