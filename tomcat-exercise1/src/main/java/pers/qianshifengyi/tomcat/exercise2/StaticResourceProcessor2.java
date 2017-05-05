package pers.qianshifengyi.tomcat.exercise2;

import pers.qianshifengyi.tomcat.exercise1.HttpServer1;
import pers.qianshifengyi.tomcat.util.FileUtil;

import java.io.File;
import java.io.OutputStream;

/**
 * Created by zhangshan on 17/5/3.
 */
public class StaticResourceProcessor2 {

    private Request2 request2;
    private Response2 response2;

    public StaticResourceProcessor2(Request2 request2,Response2 response2){
        this.request2 = request2;
        this.response2 = response2;
    }

    public void process(){
        OutputStream os = response2.getOs();
        String resourcesPath = Constants.WEBAPPS+ request2.getUri();

        try {
            File file = new File(resourcesPath);
            System.out.println("file.exists():"+file.exists()+"    resourcesPath:"+resourcesPath);
            if(!file.exists()){
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                os.write(errorMessage.getBytes());
            }
            FileUtil.writeFile2OS(resourcesPath,os);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            FileUtil.closeStream(os);
        }

    }

}
