package pers.qianshifengyi.dao.impl;

import org.springframework.stereotype.Component;
import pers.qianshifengyi.dao.UserDao;
import pers.qianshifengyi.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mountain on 2017/8/26.
 */

public class UserDaoImpl implements UserDao {

    @Override
    public int insert(User user) {
        return ThreadLocalRandom.current().nextInt();
    }

    @Override
    public int insertSelective(User user) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return ThreadLocalRandom.current().nextInt(100);
    }

    @Override
    public int updateSelective(User user) {
        return ThreadLocalRandom.current().nextInt(100);
    }

    @Override
    public User selectByPrimaryKey(int id) {

        return new User(id,"张 "+id+" 喵",ThreadLocalRandom.current().nextInt(30));
    }

    @Override
    public List<User> selectAll() {
        List<User> userList = new ArrayList<User>();
        for(int i=1;i<=10;i++) {
            int age = ThreadLocalRandom.current().nextInt(30);
            userList.add(new User(i,"张 "+i+" 喵",ThreadLocalRandom.current().nextInt(30)));
        }

        return userList;
    }

    @Override
    public List<User> selectForPagination(Map<String, String> params) {
        List<User> userList = new ArrayList<User>();
        for(int i=100;i<=110;i++) {
            int age = ThreadLocalRandom.current().nextInt(30);
            userList.add(new User(i,"张 "+i+" 喵",ThreadLocalRandom.current().nextInt(30)));
        }

        return userList;
    }
}
