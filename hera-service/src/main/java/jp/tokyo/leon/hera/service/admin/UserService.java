package jp.tokyo.leon.hera.service.admin;

import jp.tokyo.leon.hera.dao.entity.admin.User;

/**
 * @author leon
 * @date 2024/2/12 21:31
 */

public interface UserService {
    User getUserById(Long id);
}
