package pers.qianshifengyi.tomcat.exercise15;

import java.util.*;

/**
 * Created by zhangshan on 17/6/13.
 */
public class Person {
    private int id;
    private String category;
    private String try1;
    private String name;
    private String gender;
    private String emails;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTry1(String try1) {
        this.try1 = try1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(String key, String value) {
        this.emails=key+"    "+value;
    }

    public void print() {
        System.out.println("id==" + id + " category=" + category + " try1=" + try1
                +" name=" + name + " gender=" + gender+" email="+emails);

    }

}
