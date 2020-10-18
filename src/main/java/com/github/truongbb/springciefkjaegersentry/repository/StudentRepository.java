package com.github.truongbb.springciefkjaegersentry.repository;


import com.github.truongbb.springciefkjaegersentry.dto.Student;

import java.util.List;

/**
 * @author truongbb
 */
public interface StudentRepository {

    List<Student> getAll();

}
