package pers.qianshifengyi.objcopy;

/**
 * 浅复制
 * Created by mountain on 2017/8/6.
 */
public class ShallowCopyTest {

    public static void main(String[] args)throws Exception {
        Human human1 = new Human(1,new Cat());
        Object obj = new Object();
        Human human2 = (Human)(human1.clone());

        System.out.println(human1 == human2);
        System.out.println(human1.getCat() == human2.getCat());
        System.out.println("human1.getCat().hashCode():"+human1.getCat().hashCode()+"    human2.getCat().hashCode():"+human2.getCat().hashCode());

    }

}

class Human implements Cloneable{
    private Cat cat;
    private int id;

    public Human(int id,Cat cat){
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Cat{

}