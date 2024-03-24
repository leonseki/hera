package jp.tokyo.leon.hera.service;

import jp.tokyo.leon.hera.dao.entity.jpa.Student;

/**
 * @author leon
 * @date 2024/3/24 16:56
 */
public interface StudentService {
    Student findByName(String name);
}
