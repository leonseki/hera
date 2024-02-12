package jp.tokyo.leon.hera.dao.entity.admin;

import lombok.*;

import java.util.Date;

/**
 * @author lone
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;

    private String username;

    private String password;

    private String email;

    private Date createTime;

    private Date updateTime;
}