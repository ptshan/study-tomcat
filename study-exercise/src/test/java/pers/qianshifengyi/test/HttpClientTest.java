package pers.qianshifengyi.test;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.After;
import org.junit.Test;

/**
 */
public class HttpClientTest extends BaseTest {


    @Test
    public void testxxxx()throws Exception{

        httppost = new HttpPost(LOCAL_ADDR+"/xxxx/xxxx");
        paramsMap.put("aaa",3);
        paramsMap.put("bbb", "bbb");

    }




    @Override
    public CookieStore getCookieStore() {
    // anyloan_session_key=S5c983ae39dee482eb1afb89c6cdc91ee060338;
        String sessionId = "md5";
        BasicClientCookie cookie = new BasicClientCookie("sessionId",sessionId);
        cookie.setAttribute("sessionId",sessionId);
//        cookie.setVersion(0);
        // 注意domain 的 127.0.0.1 和 localhost不同
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        System.out.println("cookie:"+cookieStore.toString());

        System.out.println("httppost.getURI().toString():"+httppost.getURI().toString());
        return cookieStore;
    }
}
