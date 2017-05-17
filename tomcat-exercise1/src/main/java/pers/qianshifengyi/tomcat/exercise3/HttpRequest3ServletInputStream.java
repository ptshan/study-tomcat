package pers.qianshifengyi.tomcat.exercise3;

import pers.qianshifengyi.tomcat.exercise3.http.HttpRequest3;
import pers.qianshifengyi.tomcat.util.StringManager;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhangshan on 17/5/11.
 */
public class HttpRequest3ServletInputStream extends ServletInputStream {

    private StringManager sm = StringManager.getStringManager("pers.qianshifengyi.tomcat.exercise3");

    private boolean closed = false;

    private InputStream is;

    private int contentLength = 0;

    // 该is流已经读取返回的字节数
    private int count = 0;

    public HttpRequest3ServletInputStream(HttpRequest3 httpRequest3){
        is = httpRequest3.getIs();
        contentLength = httpRequest3.getContentLength();
    }

    @Override
    public int read() throws IOException {
        if(closed == true){
            throw new IOException(sm.getString("httpRequest3ServletInputStream.close.closed"));
        }
        if(contentLength >= 0 && count >= contentLength){
            return -1;
        }
        int b = is.read();
        if(b >= 0) {
            count++;
        }
        return b;
    }

    @Override
    public int read(byte[] b) throws IOException {
        if(closed == true){
            throw new IOException(sm.getString("httpRequest3ServletInputStream.close.closed"));
        }
        if(contentLength >= 0 && count >= contentLength){
            return -1;
        }
        int i = is.read(b);
        if(i > 0) {
            count=count+i;
        }
        return i;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if(closed == true){
            throw new IOException(sm.getString("httpRequest3ServletInputStream.close.closed"));
        }
        int toRead = len;
        if(contentLength >= 0 ){
            if(count >= contentLength) {
                return -1;
            }
            // 如果读取的字节数+len大于contentLength,则 要读取的字节数  toRead = contentLength - count
            if(count+len > contentLength){
                toRead = contentLength - count;
            }


        }
        //
        int i = is.read(b,off,toRead);
        if(i > 0) {
            count=count+i;
        }
        return i;
    }

    @Override
    public void close() throws IOException {

        if(closed == true){
            throw new IOException(sm.getString("httpRequest3ServletInputStream.close.closed"));
        }

        // 如果读取的字节数小于contentLength的长度则继续读取,直到读取完成,跳出循环
        if(contentLength > 0){
            while(count < contentLength){
                int b = read();
                if(b < 0){
                    break;
                }
            }
        }

        closed = true;
    }
}
