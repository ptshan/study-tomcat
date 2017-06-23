
StandardWrapperValve 过滤器的使用过程:

一、StandardWrapperValve类的invoke方法中,
public void invoke(Request request, Response response,ValveContext valveContext)

1、ApplicationFilterChain filterChain = createFilterChain(request, servlet);
a、ApplicationFilterChain filterChain = new ApplicationFilterChain();
b、通过getContainer()得到StandardWrapper
c、通过(StandardContext) wrapper.getParent()得到StandardContext
d、通过FilterMap filterMaps[] = context.findFilterMaps();得到StandardContext中的filterMaps数组
e、遍历filterMaps,若filterMaps[i]和requestPath匹配,
则ApplicationFilterConfig filterConfig = (ApplicationFilterConfig)context.findFilterConfig(filterMaps[i].getFilterName());
并filterChain.addFilter(filterConfig);
f、遍历filterMaps,若filterMaps[i]和servletName匹配,
则ApplicationFilterConfig filterConfig = (ApplicationFilterConfig)context.findFilterConfig(filterMaps[i].getFilterName());
filterChain.addFilter(filterConfig);
g、返回filterChain

2、filterChain.doFilter(sreq, sres);

二、ApplicationFilterChain类的doFilter方法如下:
public void doFilter(ServletRequest request, ServletResponse response)throws IOException, ServletException

private ArrayList filters = new ArrayList();
this.iterator = filters.iterator()

void addFilter(ApplicationFilterConfig filterConfig) {
        this.filters.add(filterConfig);
}
1、该方法调用internalDoFilter(request,response);
2、方法private void internalDoFilter(ServletRequest request, ServletResponse response)throws IOException, ServletException {


a、若if (this.iterator.hasNext()) 有下一个过滤器,

ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) iterator.next();
Filter filter = filterConfig.getFilter();
filter.doFilter(request, response, this);

b、若没有则说明过滤器执行完毕,执行servlet.service(request, response);

三、ApplicationFilterConfig类的构造函数
public ApplicationFilterConfig(Context context, FilterDef filterDef)
1、在构造函数中执行:
this.context = context;
setFilterDef(filterDef);
2、setFilterDef方法调用getFilter() 貌似没啥用
3、getFilter()方法
a、通过filterDef.getFilterClass()得到filterClass
b、根据filterClass的包名判断使用catalina包的当前类加载器还是WebappClassLoader的web应用加载器
c、根据classLoader.loadClass(filterClass)得到Filter的类,通过this.filter = (Filter) clazz.newInstance();filter.init(this);
得到初始化后的Filter类然后返回









