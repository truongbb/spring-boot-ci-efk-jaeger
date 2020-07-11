package vn.com.ntqsolution.springciefk.repository;


import vn.com.ntqsolution.springciefk.dto.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAll();
}
