package pers.qianshifengyi.tomcat.exercise15;

import org.apache.commons.digester.Digester;

/**
 * Created by zhangshan on 17/6/12.
 */
public class DigesterTest {

    public static void main(String[] args)throws Exception {
        //parseViewCache3();
        parseAddressBooks();
    }

    public static void parseViewCache()throws Exception{
        Digester digester = new Digester();

        digester.setValidating(false);

        digester.addObjectCreate("viewcache/areas",ViewCache.class);
        digester.addSetProperties("viewcache/areas");

        digester.addObjectCreate("viewcache/areas/area",Area.class);
        digester.addSetProperties("viewcache/areas/area");
        digester.addSetNext("viewcache/areas/area","addArea");

        // 每个对象的类型都要手工去设置
        digester.addCallMethod("viewcache/areas/area/id","setId",1,new Class[]{Integer.class});
        digester.addCallParam("viewcache/areas/area/id",0);

        digester.addCallMethod("viewcache/areas/area/parentId","setParentId",1,new Class[]{Integer.class});
        digester.addCallParam("viewcache/areas/area/parentId",0);
        digester.addCallMethod("viewcache/areas/area/areaType","setAreaType",1);
        digester.addCallParam("viewcache/areas/area/areaType",0);
        digester.addCallMethod("viewcache/areas/area/ordering","setOrdering",1,new Class[]{Integer.class});
        digester.addCallParam("viewcache/areas/area/ordering",0);
        digester.addCallMethod("viewcache/areas/area/phoneArea","setPhoneArea",1);
        digester.addCallParam("viewcache/areas/area/phoneArea",0);

        digester.addCallMethod("viewcache/areas/area/name","setName",2);
        digester.addCallParam("viewcache/areas/area/name",0,"type");
        digester.addCallParam("viewcache/areas/area/name",1);

        ViewCache viewCache = (ViewCache)digester.parse(DigesterTest.class.getResourceAsStream("viewcache.xml"));

        System.out.println("viewCache:"+viewCache);
        System.out.println("viewCache.getAreaList():"+viewCache.getAreaList());

        for(Area area:viewCache.getAreaList()){
            System.out.println(area);
        }

    }

    public static void parseViewCache2()throws Exception{
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("viewcache/areas",ViewCache.class);
        digester.addObjectCreate("viewcache/areas/area",Area.class);
        digester.addSetNext("viewcache/areas/area","addArea");

        // 类型自己转换
        digester.addBeanPropertySetter("viewcache/areas/area/id","id");
        digester.addBeanPropertySetter("viewcache/areas/area/parentId","parentId");
        digester.addBeanPropertySetter("viewcache/areas/area/areaType","areaType");
        digester.addBeanPropertySetter("viewcache/areas/area/ordering","ordering");
        digester.addBeanPropertySetter("viewcache/areas/area/phoneArea","phoneArea");
        digester.addCallMethod("viewcache/areas/area/name","setName",2);
        digester.addCallParam("viewcache/areas/area/name",0,"type");
        digester.addCallParam("viewcache/areas/area/name",1);

        ViewCache viewCache = (ViewCache)digester.parse(DigesterTest.class.getResourceAsStream("viewcache.xml"));

        System.out.println("22 viewCache:"+viewCache);
        System.out.println("22 viewCache.getAreaList():"+viewCache.getAreaList());

        for(Area area:viewCache.getAreaList()){
            System.out.println(area);
        }
    }

    public static void parseViewCache3()throws Exception{
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("viewcache/areas",ViewCache.class);
        // todo 暂时没有调测成功
        digester.addSetProperties("viewcache/areas");

        digester.addObjectCreate("viewcache/areas/area",Area.class);
        // todo 暂时没有调测成功
        digester.addSetProperties("viewcache/areas/area");

        digester.addSetNext("viewcache/areas/area","addArea");

        ViewCache viewCache = (ViewCache)digester.parse(DigesterTest.class.getResourceAsStream("viewcache.xml"));

        System.out.println("33 viewCache:"+viewCache);
        System.out.println("33 viewCache.getAreaList():"+viewCache.getAreaList());

        for(Area area:viewCache.getAreaList()){
            System.out.println(area);
        }
    }

    /*
    public static void main(String[] args) throws IOException, SAXException {
  String filename = System.getProperty("user.dir")
    + "\\src\\test2\\me2.xml";
  File file = new File(filename);
  Digester d = new Digester();
  AddressBook ab = new AddressBook();
  d.push(ab);
  addRule(d);
  d.parse(file);
  ab.print();
 }

 private static void addRule(Digester d) {
  // 对AddressBook节点注入属性设置规则,下面有详解
  d.addSetProperties("address-book");

  // 当遇到<person>时，创建类Person的一个实例，并将其压入栈顶
  d.addObjectCreate("address-book/person", Person.class);

  // 对person节点注入属性设置规则，即在SAX的事件遭遇到person节点中的Attributes时，
  // 根据属性列表中的属性值对，这儿就是 id="1", category="acquaintance",try1="would be ignored"
  // 使用Java反射(reflection)机制，调用当前栈顶对象即Person实例类中id, category,try1
  // 属性的标准的JavaBean方法，setId, setCategory,setTry1
  d.addSetProperties("address-book/person");

  // 对person节点注入父节点方法调用规则，即在SAX事件遭遇到person节点的时候，调用栈中Person实例的父实例中的addPerson方法。
  d.addSetNext("address-book/person", "addPerson");

  // 对name节点注入方法调用规则,调用当前栈顶对象即Person实例中的setName方法，而此方法的参数即是当前name节点的字符内容。
  // 通常这个规则和addCallParam规则配合使用，这儿是一种特殊情况,即没有多个参数
  d.addCallMethod("address-book/person/name", "setName", 0);

  // 和下面的email一样
  d.addCallMethod("address-book/person/gender", "setGender", 1);
  d.addCallParam("address-book/person/gender", 0, "result");

  // 对email节点注入方法调用规则，调用当前栈顶对象即Person实例中的addEmail方法，此方法需要两个参数，
  // 一个是从属性值的 type属性获取，一个是从email本身的字符内容获取。
  d.addCallMethod("address-book/person/email", "add", 2);
  d.addCallParam("address-book/person/email", 0, "type");
  d.addCallParam("address-book/person/email", 1);
 }
}
     */


    public static void parseAddressBooks()throws Exception{
        Digester digester = new Digester();
        digester.setValidating(false);
        AddressBook addressBook = new AddressBook();
        digester.push(addressBook);

        digester.addSetProperties("address-book");
        digester.addObjectCreate("address-book/person",Person.class);
        digester.addSetProperties("address-book/person");
        digester.addSetNext("address-book/person","addPerson");

        digester.addCallMethod("address-book/person/email","add",2);
        digester.addCallParam("address-book/person/email",0,"type");
        digester.addCallParam("address-book/person/email",1);

        digester.addCallMethod("address-book/person/name","setName",1);
        digester.addCallParam("address-book/person/name",0);

        digester.addCallMethod("address-book/person/gender","setGender",1);
        digester.addCallParam("address-book/person/gender",0);



        digester.parse(DigesterTest.class.getResourceAsStream("addressBooks.xml"));
        addressBook.print();

    }

}
