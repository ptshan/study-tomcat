package pers.qianshifengyi.entity;

import java.io.Serializable;

/**
 * Created by mountain on 2017/8/29.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 2795878004696894257L;

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
