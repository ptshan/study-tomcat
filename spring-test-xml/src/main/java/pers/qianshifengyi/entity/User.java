package pers.qianshifengyi.entity;

import java.io.Serializable;

/**
 * Created by mountain on 2017/8/26.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -2142598064137157176L;

    private int id;
    private String userName;
    private int age;

    public User(){

    }

    public User(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
