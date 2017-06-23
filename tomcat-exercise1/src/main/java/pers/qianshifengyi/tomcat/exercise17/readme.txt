
Bootstrap类
Bootstrap 类的主方法还构造了三个加载器用于不同的目的。使用不同加载器的 主要原因是防止 WEB-INF/classes 以及 WEB-INF/lib 下面的 类。
%CATALINE_HOME%/common/lib 目录下的 jar 包也可以访问。
这三个类加载器如下定义:
// Construct the class loaders we will need
    ClassLoader commonLoader = null;
    ClassLoader catalinaLoader = null;
    ClassLoader sharedLoader = null;
每一个类加载器都给定一个可以访问的路径。
1、commonLoader 可以访问如下目录 的类:%CATALINA_HOME%/common/classes,
%CATALINA_HOME%/common/endorsed 和 %CATALINA_HOME%/common/lib。

2、catalinaLoader 负责加载 Catalina 容器要求的类,它可以加 载%CATALINA_HOME%/server/classes 和%CATALINA_HOME%/server/lib 目录下面
的类。

3、sharedLoader 可以访问%CATALINA_HOME%/shared/classes 和%CATALJNA_HOME%/shared/lib 目录下的类以及 commondLoader 类可以访问的 类。
sharedLoader 是该 Tomcat 容器相关联的所有 web 应用的类加载器的父类加 载器。

注意一点是 sharedLoader 加载器不能加载 Catalina 的内部类加载器以及环境变 量下面的 CLASSPATH 下面的类,

















