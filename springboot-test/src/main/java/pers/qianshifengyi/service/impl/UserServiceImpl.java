package pers.qianshifengyi.service.impl;

import org.springframework.stereotype.Service;
import pers.qianshifengyi.dao.UserDao;
import pers.qianshifengyi.entity.User;
import pers.qianshifengyi.service.UserService;

import javax.annotation.Resource;

/**
 * Created by mountain on 2017/8/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User selectById(int id) {
        return userDao.selectByPrimaryKey(id);
    }
}
