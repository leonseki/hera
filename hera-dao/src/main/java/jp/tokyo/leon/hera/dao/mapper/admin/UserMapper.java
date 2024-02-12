package jp.tokyo.leon.hera.dao.mapper.admin;

import java.util.List;
import jp.tokyo.leon.hera.dao.entity.admin.User;
import jp.tokyo.leon.hera.dao.entity.admin.UserExample;
import org.apache.ibatis.annotations.Param;

/**
 * @author lone
 */
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}