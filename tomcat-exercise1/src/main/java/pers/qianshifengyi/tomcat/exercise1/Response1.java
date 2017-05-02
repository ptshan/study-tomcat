package pers.qianshifengyi.tomcat.exercise1;

import pers.qianshifengyi.tomcat.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * Created by zhangshan on 17/4/28.
 */
public class Response1 {

    private Request1 request1;
    private OutputStream os;

    public Response1(OutputStream os){
        this.os = os;
    }

    public void setRequest1(Request1 request1){
        this.request1 = request1;
    }

    public void sendStaticResource(){
        String resourcesPath = HttpServer1.WEBAPPS+ request1.getUri();

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
