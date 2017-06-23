
主要涉及的类:
1、public final class StandardWrapper extends ContainerBase implements ServletConfig, Wrapper {
2、public class ManagerServlet extends HttpServlet implements ContainerServlet {


LifecycleListener, ContainerListener, PropertyChangeListener, InstanceListener

三、java的classloader,实验的整个过程都是用java javac 进行的,没有使用idea
1、测试方式是在pers.qianshifengyi.tomcat.exercise19.Test和pers.qianshifengyi.tomcat.exercise19.Test2,
在Test类中,使用ClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL(),classFile.toURI().toURL()});加载
ansirQa.jar和Class test2Class = classLoader.loadClass("pers.qianshifengyi.tomcat.exercise19.Test2");
用urlClassLoader加载ansirQa.jar和Test2,然后在Test2中,调用convertUtilsClass =classLoader.loadClass("com.pingan.qhzx.anshao.model.dto.ansirqa.DicDesc");
然后调用强制转换 DicDesc dicDesc = (DicDesc)convertUtilsClass.newInstance();

前提:javac -cp /Users/zhangshan193/Downloads/lib_test/ansirQa.jar  /Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/java/pers/qianshifengyi/tomcat/exercise19/Test2.java
编译的时候需要ansirQa.jar,运行时由于是Test2和DicDesc都在urlClassLoader下面,所以Test2是可以运行的,并且认识Test2中的import com.pingan.qhzx.anshao.model.dto.ansirqa.DicDesc;

2、注意:
a、由于将Test和Test2都编译在了src的exercise19目录下面,开始的时候,总在src的exercise19目录下面执行java pers/qianshifengyi/tomcat/exercise19/Test,这时,Test2.class
也在src的exercise19目录下面,而Test.class是用AppClassLoader加载的,调用urlClassLoader加载Test2时,urlClassLoader会委派其父加载器AppClassLoader
来加载,而AppClassLoader在src的exercise19目录下面找到了Test2.class,故虽然使用了urlClassLoader加载Test2,但打印Test2加载器时总显示AppClassLoader
b、解决的办法是,将Test2.java干掉(目的是在target下的编译路径中,去掉Test2.class,防止被appClassLoader加载到),这是将目录切换到
cd  /Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/target/classes/(java编译路径)
然后执行java pers/qianshifengyi/tomcat/exercise19/Test,这时Test2和ansirQa就在同一个类加载器中了
c、Test2.java用idea编译不过是因为没有将ansirQa.jar放到环境变量中,而javac -cp /Users/zhangshan193/Downloads/lib_test/ansirQa.jar  /Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/java/pers/qianshifengyi/tomcat/exercise19/Test2.java
在编译时添加了ansirQa.jar 用于编译
d、编译只要依赖的class类在classpath存在就能过,运行时需要依赖的类在类加载器中存在才可以
e、DicDesc dicDesc = (DicDesc)convertUtilsClass.newInstance(); 在src的exercise19目录下面执行java pers/qianshifengyi/tomcat/exercise19/Test调用Test2
报错是因为,在src的exercise19目录下面执行Test类调用时,Test2使用的是系统类加载器AppClassLoader,调用时,DicDesc不在AppClassLoader中,而在其
子加载器URLClassLoader中,加载器只能子加载器委托父加载器,而不能反过来,故报错
f、URLClassLoader加载jar文件要具体到ansirQa.jar,不能只写加载的目录,加载class要写目录
    String jarPath = "/Users/zhangshan193/Downloads/lib_test/ansirQa.jar";
    String classPath = "/Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/java/";
    File jarFile = new File(jarPath);
    File classFile = new File(classPath);
    ClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL(),classFile.toURI().toURL()});











































