package vn.com.ntqsolution.springciefk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.springciefk.dto.Student;
import vn.com.ntqsolution.springciefk.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAll() {
        logger.error("call Service");
        return studentRepository.getAll();
    }
}
