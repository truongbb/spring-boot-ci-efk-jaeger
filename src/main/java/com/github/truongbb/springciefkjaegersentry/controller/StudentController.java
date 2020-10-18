package com.github.truongbb.springciefkjaegersentry.controller;

import com.github.truongbb.springciefkjaegersentry.dto.Student;
import com.github.truongbb.springciefkjaegersentry.service.StudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author truongbb
 */
@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${spring.data.rest.base-path}/student")
public class StudentController {

    StudentService studentService;

    @PostMapping("/getAll")
    public ResponseEntity<List<Student>> getAll() {
        log.debug("call Controller");
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

}
