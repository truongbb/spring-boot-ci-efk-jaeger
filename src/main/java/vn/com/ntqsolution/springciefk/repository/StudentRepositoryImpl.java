package vn.com.ntqsolution.springciefk.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import vn.com.ntqsolution.springciefk.dto.Student;

import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    @Override
    public List<Student> getAll() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            System.out.println("4444444444444444444444444444");
            logger.error("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            logger.error(e.getMessage(), e);
        }

        logger.info("call repository");

        return Arrays.asList(new Student(1, "Nguyen Van 1", "Ha Noi 1"),
                new Student(1, "Nguyen Van 2", "Ha Noi 2"),
                new Student(1, "Nguyen Van 3", "Ha Noi 3"),
                new Student(1, "Nguyen Van 4", "Ha Noi 4"),
                new Student(1, "Nguyen Van 5", "Ha Noi 5"),
                new Student(1, "Nguyen Van 6", "Ha Noi 6"),
                new Student(1, "Nguyen Van 7", "Ha Noi 7"),
                new Student(1, "Nguyen Van 8", "Ha Noi 8"),
                new Student(1, "Nguyen Van 9", "Ha Noi 9"),
                new Student(1, "Nguyen Van 10", "Ha Noi 10"));
    }
}
