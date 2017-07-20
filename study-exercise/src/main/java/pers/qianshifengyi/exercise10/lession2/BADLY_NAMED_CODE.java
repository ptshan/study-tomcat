package pers.qianshifengyi.exercise10.lession2;

/**
 * cd /Users/zhangshan193/Documents/projects/git_project/study-tomcat/study-exercise/src/main/java/
 * javac pers/qianshifengyi/exercise10/lession2/NameChecker.java
 * javac pers/qianshifengyi/exercise10/lession2/NameCheckProcessor.java
 * javac -processor pers.qianshifengyi.exercise10.lession2.NameCheckProcessor pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java
 * 还可以使用 -XprintRounds 和 -XprintProcessorInfo 参数来查看注解处理器运作的详细信息
 *
 *
 * todo 结果:
 * 警告: No SupportedSourceVersion annotation found on pers.qianshifengyi.exercise10.lession2.NameCheckProcessor, returning RELEASE_6.
 * 警告: 来自注释处理程序 'pers.qianshifengyi.exercise10.lession2.NameCheckProcessor' 的受支持 source 版本 'RELEASE_6' 低于 -source '1.8'
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:11: 警告: 名称"BADLY_NAMED_CODE"应当符合驼峰命名法
 * public class BADLY_NAMED_CODE {
 * ^
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:13: 警告: 名称"colors"应当以大写字母开头
 * enum colors{
 * ^
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:14: 警告: 常量"red"应当全部以大写字母或下划线来命名,并且以字母开头
 * red,blue,green;
 * ^
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:14: 警告: 常量"blue"应当全部以大写字母或下划线来命名,并且以字母开头
 * red,blue,green;
 * ^
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:14: 警告: 常量"green"应当全部以大写字母或下划线来命名,并且以字母开头
 * red,blue,green;
 * ^
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:17: 警告: 常量"_FORTY_TWO"应当全部以大写字母或下划线来命名,并且以字母开头
 * static final int _FORTY_TWO = 42;
 * ^
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:18: 警告: 名称"NOT_A_CONSTANT"应当以小写字母开头
 * public static int NOT_A_CONSTANT = _FORTY_TWO;
 * ^
 * pers/qianshifengyi/exercise10/lession2/BADLY_NAMED_CODE.java:20: 警告: 一个普通方法"BADLY_NAMED_CODE"不应当与类名重复,避免与构造函数产生混淆
 * protected void BADLY_NAMED_CODE(){
 * ^
 * 10 个警告
 *
 *
 * Created by zhangshan on 17/7/12.
 */
public class BADLY_NAMED_CODE {

    enum colors{
        red,blue,green;
    }

    static final int _FORTY_TWO = 42;
    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE(){
        return;
    }

    public void NotCamelCASEmethodNAME(){
        return;
    }

}
