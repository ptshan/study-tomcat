package pers.qianshifengyi.dao;

import pers.qianshifengyi.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by mountain on 2017/8/26.
 */
public interface UserDao {

    int insert(User user);

    int insertSelective(User user);

    int delete(int id);

    int update(User user);

    int updateSelective(User user);

    User selectByPrimaryKey(int id);

    List<User> selectAll();

    List<User> selectForPagination(Map<String, String> params);

}
