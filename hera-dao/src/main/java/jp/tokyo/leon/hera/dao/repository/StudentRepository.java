package jp.tokyo.leon.hera.dao.repository;

import jp.tokyo.leon.hera.dao.entity.jpa.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author leon
 * @date 2024/3/24 16:31
 */
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Optional<Student> findStudentByName(String name);

}
