package jp.tokyo.leon.hera.service.impl;

import jp.tokyo.leon.hera.dao.entity.jpa.Student;
import jp.tokyo.leon.hera.dao.repository.StudentRepository;
import jp.tokyo.leon.hera.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author leon
 * @date 2024/3/24 16:57
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findByName(String name) {
       return studentRepository.findStudentByName(name).orElse(null);
    }
}
