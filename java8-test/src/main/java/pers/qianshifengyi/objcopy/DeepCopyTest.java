package pers.qianshifengyi.objcopy;

/**
 * 深拷贝就是浅拷贝的递归拷贝
 * 1、浅拷贝 对象内部：1、基础类型  值复制  2、引用类型 只拷贝地址
 * 2、深拷贝 对象内部：1、基础类型  值复制  2、引用类型 所引用的对象也要clone一份，指向新对象的地址
 *
 * 3、克隆方法用于创建对象的拷贝，为了使用clone方法，类必须实现java.lang.Cloneable接口重写protected方法clone，
 *    如果没有实现Clonebale接口会抛出CloneNotSupportedException
 * 4、在克隆java对象的时候不会调用构造器
 *
 * 5、clone好处：不用初始化对象，就能动态的获得对象的运行时状态，克隆隐藏了对象创建细节，不用执行构造函数，所以性能有所提升
 *
 * Created by mountain on 2017/8/6.
 */
public class DeepCopyTest {

    public static void main(String[] args) throws Exception{
        DeepHuman human1 = new DeepHuman(1,new Dog());
        DeepHuman human2 = (DeepHuman) human1.clone();
        System.out.println(human1 == human2);
        System.out.println(human1.getDog() == human2.getDog());
        System.out.println("human1.getDog().hashCode():"+human1.getDog().hashCode()+"    human2.getDog().hashCode():"+human2.getDog().hashCode());

        System.out.println("human1.getBuf().hashCode():"+human1.getBuf().hashCode()+"    human2.getBuf().hashCode():"+human2.getBuf().hashCode());
    }

}

class DeepHuman implements Cloneable{

    private int id;

    private Dog dog;

    private byte[] buf = new byte[48];

    public byte[] getBuf() {
        return buf;
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
    }

    public DeepHuman(int id, Dog dog) {
        this.id = id;
        this.dog = dog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public Object clone()throws CloneNotSupportedException{
        DeepHuman deepHuman = (DeepHuman) super.clone();
        // 深拷贝要把对象内部对象也进行递归拷贝
        deepHuman.setDog((Dog)this.dog.clone());
        deepHuman.setBuf(deepHuman.buf.clone());
        return deepHuman;
    }

}

class Dog implements Cloneable{

    public Dog(){
        System.out.println("--- dog init -----");
    }

    @Override
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}