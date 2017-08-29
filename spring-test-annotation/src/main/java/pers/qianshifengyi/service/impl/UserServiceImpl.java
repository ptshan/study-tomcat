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
// 默认采用UserServiceImpl第一个字母小写作为bean的id或name
@Service("userService")
public class UserServiceImpl implements UserService {

    // @Resource 优先按照byName查找,再按照byType  @Resource（这个注解属于J2EE的）,若指定了name则只按照name查找
    // @Resource(name="")

    // @Autowired （这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，如果要允许null 值，
    // 可以设置它的required属性为false，如：@Autowired(required=false)
    // todo @Autowired 默认按照类型装配？ 从我的配置来看这个结论是不对的
    // todo Because @Autowired is used to perform autowiring by type, if you have multiple bean definitions of the same type, you cannot rely on this approach for those particular beans. In that case, you can use @Autowired in conjunction with @Qualifier.

    @Autowired
    @Qualifier("userDaoImpl") //使用名称装配，可以和@Autowired搭配使用
    private UserDao userDao;

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
