package pers.qianshifengyi.test;

/**
 * Created by zhangshan193 on 17/3/30.
 */
public class ZSStringUtils {

    public static String substring(int beginIndex, int endIndex) {
        return "everything in the word is good".substring(beginIndex,endIndex);
    }

    /**
     * 得到第position个单词的下标
     * @param position
     * @param str
     * @return
     */
    public static String getWord(int position,String str) {
        str = str.trim();
        String[] strArrs = str.split(" ");
        return strArrs[position-1];
    }
}
