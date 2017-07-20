package pers.qianshifengyi.exercise10.lession1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangshan on 17/7/12.
 */
public class SyntacticSugarTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("hello","你好");
        map.put("how are you?","你吃了吗?");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
    }

    /**
     * 反编译后
     public static void main(String[] args) {
     HashMap map = new HashMap();
     map.put("hello", "你好");
     map.put("how are you?", "你吃了吗?");
     System.out.println((String)map.get("hello"));
     System.out.println((String)map.get("how are you?"));
     }
     */


}
