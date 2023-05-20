package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

    ArrayList<Student> student_list=new ArrayList<>();
    ArrayList<Teacher> teacher_list=new ArrayList<>();
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        student_list.add(student);
        return new ResponseEntity<>("New student added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        teacher_list.add(teacher);
        return new ResponseEntity<>("New teacher added successfully", HttpStatus.CREATED);
    }

    HashMap<String, String> hm=new HashMap<>();
    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam String student, @RequestParam String teacher){
        hm.put(student, teacher);
        return new ResponseEntity<>("New student-teacher pair added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        Student student = null; // Assign student by calling service layer method
        for(int i=0;i<student_list.size();i++){

            if(name==student_list.get(i)+""){
                student=student_list.get(i);
            }
        }
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        Teacher teacher = null; // Assign student by calling service layer method
        for(int i=0;i<teacher_list.size();i++){

            if(name==teacher_list.get(i)+""){
                teacher=teacher_list.get(i);
            }
        }
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
        List<String> students = null; // Assign list of student by calling service layer method
        for(int i=0;i<hm.size();i++){
            if(teacher==hm.get(i)){
                students.add(hm.get(i));
            }
        }
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        List<String> students = null; // Assign list of student by calling service layer method
        for (int i=0;i<student_list.size();i++){
            students.add(student_list.get(i)+"");
        }
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher){
        for (int i=0;i<teacher_list.size();i++){
            if(teacher==teacher_list.get(i)+""){
                teacher_list.remove(i);
            }
        }
        return new ResponseEntity<>(teacher + " removed successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){
        for(int i=0;i<teacher_list.size();i++){
            teacher_list.remove(i);
        }
        return new ResponseEntity<>("All teachers deleted successfully", HttpStatus.CREATED);
    }
}
