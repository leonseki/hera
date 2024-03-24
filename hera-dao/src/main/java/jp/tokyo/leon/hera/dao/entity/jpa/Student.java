package jp.tokyo.leon.hera.dao.entity.jpa;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author leon
 * @date 2024/3/24 16:23
 */
@Setter
@Getter
@Entity(name = "student")
public class Student extends BaseEntity{

    private String name;

    private Integer age;

    private Integer classNo;
}
