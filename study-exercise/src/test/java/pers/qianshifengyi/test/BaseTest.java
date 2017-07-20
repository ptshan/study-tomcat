package pers.qianshifengyi.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.*;

/**
 * Date: 16-6-30
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseTest {
    private String securitySignPrefix="aaaa";
    private String securitySignSuffix="md5";

    private CloseableHttpClient httpclient = null;
    private List<NameValuePair> formparams = null;
    public Map<String,Object> paramsMap = null;
    private UrlEncodedFormEntity uefEntity;
    public HttpPost httppost;
    public HttpGet httpget;
    private CloseableHttpResponse response;
    private HttpEntity entity;
    public static final String LOCAL_ADDR = "http://127.0.0.1:8081/aaaa/bbbb";

    public CookieStore cookieStore = new BasicCookieStore();

    public abstract CookieStore getCookieStore();

    @Before
    public void init(){
        //httpclient = HttpClients.createDefault();
        System.out.println("getCookieStore():"+getCookieStore());


        formparams = new ArrayList<NameValuePair>();
        paramsMap = new HashMap<String,Object>();

        // formparams.add(new BasicNameValuePair("appId", ""));
    //    formparams.add(new BasicNameValuePair("appId", "3322"));
        //formparams.add(new BasicNameValuePair("token", ""));
    //    formparams.add(new BasicNameValuePair("token", "5566"));

    }

    /**
     * 打印返回请求
     */
    public Map<String,Object> printResponse()throws Exception{
        String jsonStr = null;
        if(httppost != null){
            StringBuilder queryString = new StringBuilder();
            Set<String> keys = paramsMap.keySet();
            for(String key:keys){
                formparams.add(new BasicNameValuePair(key, paramsMap.get(key).toString()));
                queryString.append(key).append("=").append(paramsMap.get(key)).append("&");
            }
            if(queryString.length()>1) {
                queryString.deleteCharAt(queryString.length() - 1);
            }
            queryString.insert(0, securitySignPrefix).append(securitySignSuffix);
            String md5Str = Hashing.md5().hashString(queryString.toString(), Charsets.UTF_8).toString();
            System.out.println("----- request -------");
            if(paramsMap.isEmpty()){
                System.out.println(httppost.getURI()+"?sign="+md5Str);
            }else{
                System.out.println(httppost.getURI()+"?"+queryString.toString()+"&sign="+md5Str);
            }
            System.out.println("--------------------------------------");

            formparams.add(new BasicNameValuePair("sign", md5Str));

            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("------- header ----------");
            for(Header header:httppost.getAllHeaders()){
                System.out.println(header.toString());
            }
            httpclient = HttpClients.custom().setDefaultCookieStore(getCookieStore()).build();
            response = httpclient.execute(httppost);
        }else{
            System.out.println(httpget.getURI());
            response = httpclient.execute(httpget);

        }
        System.out.println("response:"+response);
        entity = response.getEntity();
        if (entity != null) {
            System.out.println("------------------Response content--------------------");
            jsonStr = EntityUtils.toString(entity, "UTF-8");
            System.out.println(jsonStr);
            System.out.println("--------------------------------------");
        }else{
            System.out.println(" entity is null");
        }
        Map<String,Object> resultMap = (Map<String,Object>) JSONObject.parseObject(jsonStr, Map.class);
        return resultMap;
    }

    @After
    public void close(){
        if(response != null){
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if(httpclient != null){
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public String filterSpecialCharacter(String str){
        String temp = str.replaceAll("\"", "%22").replaceAll("\\{", "%7b").replaceAll("}", "%7d");
        return temp;
    }

}
