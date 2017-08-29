package pers.qianshifengyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.qianshifengyi.dao.UserDao;
import pers.qianshifengyi.entity.User;
import pers.qianshifengyi.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by mountain on 2017/8/26.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int insert(User user) {
        System.out.println("UserServiceImpl insert 执行");
        return userDao.insert(user);
    }

    @Override
    public int insertSelective(User user) {
        System.out.println("UserServiceImpl insertSelective 执行");
        return userDao.insertSelective(user);
    }

    @Override
    public int delete(int id) {
        System.out.println("UserServiceImpl delete 执行");
        return userDao.delete(id);
    }

    @Override
    public int update(User user) {
        System.out.println("UserServiceImpl update 执行");
        return userDao.update(user);
    }

    @Override
    public int updateSelective(User user) {
        System.out.println("UserServiceImpl updateSelective 执行");
        return userDao.updateSelective(user);
    }

    @Override
    public User selectByPrimaryKey(int id) {
        System.out.println("UserServiceImpl selectByPrimaryKey 执行");
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        int i=1;
        if(i == 1) {
            throw new RuntimeException("挂了");
        }
        System.out.println("UserServiceImpl selectAll 执行");
        return userDao.selectAll();
    }

    @Override
    public List<User> selectForPagination(Map<String, String> params) {
        System.out.println("UserServiceImpl selectForPagination 执行");
        return userDao.selectForPagination(params);
    }

}
