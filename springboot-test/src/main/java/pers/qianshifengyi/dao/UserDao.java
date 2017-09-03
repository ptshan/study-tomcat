package pers.qianshifengyi.dao;

import pers.qianshifengyi.entity.User;

/**
 * Created by mountain on 2017/8/29.
 */
public interface UserDao {

    User selectByPrimaryKey(int id);


}
