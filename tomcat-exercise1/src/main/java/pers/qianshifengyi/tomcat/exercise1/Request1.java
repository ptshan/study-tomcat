package pers.qianshifengyi.tomcat.exercise1;

import java.io.InputStream;

/**
 * Created by zhangshan193 on 17/4/28.
 */
public class Request1 {

    private InputStream is;
    private String uri;

    public Request1(InputStream is){
        this.is = is;
    }

    public void parse(){

    }

    public String getUri(){
        return uri;
    }


}
