package pers.qianshifengyi.tomcat.exercise15;

import java.util.*;

/**
 * Created by zhangshan on 17/6/13.
 */
public class AddressBook {
    private String name1;
    private List<Person> list = new ArrayList<Person>();

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void addPerson(Person p) {
        this.list.add(p);
    }


    public void print() {
        System.out.println("============now AddressBook:===============");
        System.out.println("name1 == " + name1);
        System.out.println("List Size == " + list.size());
        System.out.println("============now Person:===============");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Person " + i);
            list.get(i).print();
        }
    }
}