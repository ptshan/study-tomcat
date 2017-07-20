package pers.qianshifengyi.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Cookie 使用原理是将login的cookie全部放到下一个接口的cookie中
 * Date: 16-9-9
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public class BaseHttpClientTest {

    private String securitySignPrefix="aaaa";
    private String securitySignSuffix="_md5";
    public static final String Local_Url = "https://xxxxxxxx.com.cn:8081/test_app/test";
    public static ContentType UTF8ContentType = ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8);
    private static final String downloadPath = System.getProperty("user.home")+"/Downloads/";

    BasicCookieStore basicCookieStore = new BasicCookieStore();
    List<Cookie> cookieList;

    public HttpEntity buildCommonQueryString(Map<String, Object> map) {


        StringBuilder queryString = new StringBuilder();
        Set<String> keySet = map.keySet();
        List<String> keyList = new ArrayList(keySet);

        Collections.sort(keyList,new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        // file类型不参与验签拼接
        for(String key:keyList){
            String value = map.get(key).toString();
            if(value.toUpperCase().trim().startsWith("FILE:")){
                String filePath = value.replaceFirst("[Ff][iI][lL][eE]:","");
                entityBuilder.addPart(key, new FileBody(new File(filePath)));
            }else{
                queryString.append(key).append("=").append(map.get(key)).append("&");
                System.out.println("key:"+key+",value:"+value);
                entityBuilder.addPart(key, new StringBody(value, UTF8ContentType));
//                entityBuilder.addPart(key, new StringBody(value, ContentType.APPLICATION_FORM_URLENCODED));
            }
        }
        if(queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        queryString.insert(0, securitySignPrefix).append(securitySignSuffix);
        System.out.println(queryString);
        map.put("sign", Hashing.md5().hashString(queryString.toString(), Charsets.UTF_8).toString());

        entityBuilder.addPart("sign", new StringBody(map.get("sign").toString(), UTF8ContentType));

        return entityBuilder.setCharset(Charset.forName("UTF-8")).build();
    }

    public JSONObject getAndPrintResponse(String url,HttpEntity entity)  {
    	System.out.println("entity:"+entity);
        CloseableHttpResponse response = null;
        JSONObject result = null;
        HttpPost httppost = new HttpPost(Local_Url+url);
        if(cookieList != null && !cookieList.isEmpty()){
            for(Cookie cookie:cookieList){
                basicCookieStore.addCookie(cookie);
            }
        }

        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(basicCookieStore).build();
        try {
            System.out.println("executing request " + httppost.getRequestLine());
            httppost.setEntity(entity);
            response = httpclient.execute(httppost);
            if(cookieList == null || cookieList.isEmpty()) {
                cookieList = basicCookieStore.getCookies();
            }
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
            String returnFileName = "";
            if(isReturnFile(response,returnFileName) == true){
                result = JSON.parseObject(returnFileName);
            }else {
                if (resEntity != null) {
                    String resStr = EntityUtils.toString(resEntity);
                    result = JSON.parseObject(resStr);
                }

                EntityUtils.consume(resEntity);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try{
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                httpclient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean isReturnFile(HttpResponse response, String returnFileName) {
        Header contentHeader = response.getFirstHeader("Content-disposition");

        if(contentHeader == null){
            return false;
        }
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        //filename = new String(param.getValue().toString().getBytes(), "utf-8");
                        //filename=URLDecoder.decode(param.getValue(),"utf-8");
                        filename = param.getValue();
                        returnFileName = filename;
                        File file = new File(downloadPath+filename);
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        InputStream is = response.getEntity().getContent();
                        OutputStream os = new FileOutputStream(file);
                        byte[] buf = new byte[10240];
                        int i = is.read(buf);
                        while(i != -1){
                            os.write(buf,0,i);
                            i = is.read(buf);
                        }
                        os.flush();
                        os.close();
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    return false;
                }
            }
        }
        return true;
    }



}
