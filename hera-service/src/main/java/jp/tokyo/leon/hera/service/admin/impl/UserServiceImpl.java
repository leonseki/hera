package jp.tokyo.leon.hera.service.admin.impl;

import jp.tokyo.leon.hera.dao.entity.admin.User;
import jp.tokyo.leon.hera.dao.mapper.admin.UserMapper;
import jp.tokyo.leon.hera.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leon
 * @date 2024/2/12 21:34
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
