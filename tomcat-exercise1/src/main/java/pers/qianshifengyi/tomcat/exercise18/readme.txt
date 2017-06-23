org.apache.catalina.startup.Catalina 是一个启动类,它使用一个 Digester 对象将 server.xml 文档中的 XML 元素转换为 Java 对象。
Catalina 类定义了 createStartDigester 方法用于往 Digester 对象添加规则。下面是该方法中的 一行
digester.addRuleSet(new HostRuleSet("Server/Service/Engine/"));
org.apache.catalina.startup.HostRuleSet 类继承了 org.apache.commons.digester.RuleSetBase 类。作为一个 RuleSetBase 类的子
类,HostRuleSet 类提供了 addRuleInstances 方法的实现,该方法用于为 RuleSet 定义规则。下面是 HostRuleSet 类的 addRuleInstances 方法的
一个片段
public void addRuleInstances(Digester digester) {
  digester.addObjectCreate(prefix + "Host",
    "org.apache.catalina.core.StandardHost", "className");
  digester.addSetProperties(prefix + "Host");
  digester.addRule(prefix + "Host",
    new CopyParentClassLoaderRule(digester));
  digester.addRule(prefix + "Host",
    new LifecycleListenerRule (digester,
    "org.apache.catalina.startup.HostConfig", "hostConfigClass"));
这段代码的意思是,当 Server/Service/Engine/Host 的模式的时候创建一个 org.apache.catalina.startup.HostConfig 类的对象,并将其作为一个
生命周 期监 器添加到主机上。换句话说,HostConfig 处理 StandardHost 的 start 方 法和 stop 方法触发的事件。
HostConfig 类的 lifecycleEvent 方法如 Listing18.1 所示。该方法用于处理事 件,
因为 HostConfig 是 StandardHost 实例的监 器,每次调用
StandardHost 启动或停止的时候,都会触发 lifecycleEvent 方法。




















































