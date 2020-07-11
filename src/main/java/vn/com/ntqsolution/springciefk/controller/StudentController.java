package vn.com.ntqsolution.springciefk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.ntqsolution.springciefk.dto.Student;
import vn.com.ntqsolution.springciefk.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}/student")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping("/getAll")
    public ResponseEntity<List<Student>> getAll(){
        logger.debug("call Controller");
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);

        
    }
}
