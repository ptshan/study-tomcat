package pers.qianshifengyi.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class HttpClientTest2 extends BaseHttpClientTest {


    @Test
    public void testGetCaptcha()throws Exception{
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("aaa",123);
        JSONObject result = getAndPrintResponse("/aaa/bbbb",buildCommonQueryString(params));
        System.out.println(JSON.toJSONString(result, true));
        String imageStr = result.getJSONObject("data").getString("aaa").replace("data:image/png;base64,","");
       // Base64UtilsTest.GenerateImage(imageStr);
    }


}
