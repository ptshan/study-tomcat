package pers.qianshifengyi.dao.impl;

import org.springframework.stereotype.Repository;
import pers.qianshifengyi.dao.UserDao;
import pers.qianshifengyi.entity.User;

/**
 * Created by mountain on 2017/8/29.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Override
    public User selectByPrimaryKey(int id) {
        User user = new User();
        user.setId(id);
        user.setName("lucy");
        return user;
    }
}
