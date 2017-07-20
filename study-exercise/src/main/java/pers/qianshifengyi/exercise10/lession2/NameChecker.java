package pers.qianshifengyi.exercise10.lession2;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;
import java.util.EnumSet;

/**
 * Created by zhangshan on 17/7/12.
 */
public class NameChecker {

    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    NameChecker(ProcessingEnvironment processingEnvironment){
        this.messager = processingEnvironment.getMessager();
    }

    /**
     * Java语言规范要求:
     * 类或接口: 符合驼峰命名法,首字母大写
     *
     * 字段:
     *   常量: 全部大写
     *   类、实例变量: 符合驼峰命名法,首字母小写
     * @param element
     */
    public void checkNames(Element element){
        nameCheckScanner.scan(element);
    }

    /**
     * 名称检查器实现类,继承了JDK1.6中新提供的ElementScanner6
     * 将会以Visitor模式访问抽象语法树中的元素
     * 分别执行  visitType()、visitVariable()、visitExecutable()
     * 来访问 类  字段   方法
     */
    private class NameCheckScanner extends ElementScanner6<Void,Void>{

        /**
         * 此方法用于检查Java类
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            scan(e.getTypeParameters(),aVoid);
            checkCamelCase(e,true);
            super.visitType(e,aVoid);

            return null;
        }

        /**
         * 检查变量命名是否规范
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)){
                checkAllCaps(e);
            }else{
                checkCamelCase(e,false);
            }
            return null;
        }

        /**
         * 检查方法命名是否合法
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if(e.getKind() == ElementKind.METHOD){
                Name name = e.getSimpleName();
                if(name.contentEquals(e.getEnclosingElement().getSimpleName())){
                    messager.printMessage(Diagnostic.Kind.WARNING,"一个普通方法\""+name+"\"不应当与类名重复,避免与构造函数产生混淆",e);
                }
            }
            super.visitExecutable(e, aVoid);
            return null;
        }

    }

    /**
     * 判断一个变量是否是常量
     * heuristically  启发式地；试探性地
     * @param e
     * @return
     */
    private boolean heuristicallyConstant(VariableElement e){
        if(e.getEnclosingElement().getKind() == ElementKind.INTERFACE){
            return true;
        }else if(e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL))){
            return true;
        }
        return false;
    }

    /**
     * 检查传入的Element是否符合驼峰命名,如果不符合,则输出警告信息
     * convenional  约定标志
     * @param e
     * @param initialCaps 为true时,当第一个字母为小写时,输入警告信息,即为true时,首字母必须大写
     *                    为false时,首字母必须小写
     *
     */
    private void checkCamelCase(Element e,boolean initialCaps){
        String name = e.getSimpleName().toString();
        boolean previousUpper = false;
        boolean convenional = true;
        int firstCodePoint = name.codePointAt(0);
        // 如果第一个字母是大写
        if(Character.isUpperCase(firstCodePoint)){
            previousUpper = true;
            if(!initialCaps){
                messager.printMessage(Diagnostic.Kind.WARNING,"名称\""+name+"\"应当以小写字母开头",e);
                return;
            }
        }else if(Character.isLowerCase(firstCodePoint)){
            if(initialCaps){
                messager.printMessage(Diagnostic.Kind.WARNING,"名称\""+name+"\"应当以大写字母开头",e);
                return;
            }
        }else{ // 数字或下划线
            convenional = false;
        }

        if(convenional){
            int cp = firstCodePoint;
            for(int i = Character.charCount(cp);i<name.length();i +=Character.charCount(cp)){
                cp = name.codePointAt(i);
                // 若cp为大写
                if(Character.isUpperCase(cp)){
                    // 若前一个字符为大写
                    if(previousUpper){
                        convenional = false;
                        break;
                    }
                    previousUpper = true;
                }else{
                    previousUpper = false;
                }
            }
        }

        // 若约定标志为false
        if(!convenional){
            messager.printMessage(Diagnostic.Kind.WARNING,"名称\""+name+"\"应当符合驼峰命名法",e);
        }

    }

    /**
     * 常量检查
     * 大写命名检查,要求第一个字母必须是大写的英文字母,其余部分可以是下划线或大写字母
     * conventional  传统的;习用的，平常的;依照惯例的;约定的
     * Underscore 底线;（表示强调的）下方划线;
     * @param e
     */
    private void checkAllCaps(Element e){
        String name = e.getSimpleName().toString();

        boolean conventional = true;
        int firstCodePoint = name.codePointAt(0);
        // 判断第一个字母是否是大写,如果不是大写,conventional为false
        if(!Character.isUpperCase(firstCodePoint)){
            conventional = false;
        }else{
            boolean previousUnderscore = false;
            int cp = firstCodePoint;
            // String 你好 = "123";
            // return codePoint >= 65536 ? 2 : 1;
            // 确定表示指定字符所需的字符的值的数目(Unicode代码点)。如果指定的字符是等于或大于0x10000，
            // 那么该方法返回2。否则，该方法返回1
            // charCount用来判断是不是Unicode编码,UTF-8,一个字符"你"占3个字节,一个int占4个字节,所以int容量
            // 足够装一个字符,汉字占3个字节,必定大于 0x10000
            for(int i = Character.charCount(cp); i<name.length();i += Character.charCount(cp)){
                cp = name.codePointAt(i);
                if(cp == (int)'_'){
                    // 防止连续两个下划线 _ _
                    if(previousUnderscore){
                        conventional = false;
                        break;
                    }
                    previousUnderscore = true;
                }else{ // 若不是下划线
                    previousUnderscore = false;
                    // 如果当前不是大写字母   且当前不为数字
                    if(!Character.isUpperCase(cp) && !Character.isDigit(cp)){
                        conventional = false;
                        break;
                    }
                }
            }
        }

        if(!conventional){
            messager.printMessage(Diagnostic.Kind.WARNING,"常量\""+name+"\"应当全部以大写字母或下划线来命名,并且以字母开头",e);
        }
    }



}
