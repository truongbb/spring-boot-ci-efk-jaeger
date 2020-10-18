package com.github.truongbb.springciefkjaegersentry.service;

import com.github.truongbb.springciefkjaegersentry.dto.Student;
import com.github.truongbb.springciefkjaegersentry.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author truongbb
 */
@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Override
    public List<Student> getAll() {
        log.error("call Service");
        return studentRepository.getAll();
    }

}
