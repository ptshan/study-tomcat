package pers.qianshifengyi.tomcat.exercise1;

import pers.qianshifengyi.tomcat.util.FileUtil;

import java.io.InputStream;

/**
 * Created by zhangshan on 17/4/28.
 */
public class Request1 {

    private InputStream is;
    private String uri;

    public Request1(InputStream is){
        this.is = is;
    }

    public void parse(){
        String content = FileUtil.getContentFromIS(is);
        System.out.println("content:"+content);
        String[] contentArr = content.split(" ");
        if(contentArr != null && contentArr.length > 1) {
            uri = contentArr[1];
        }

    }

    public String getUri(){
        return uri;
    }


}
