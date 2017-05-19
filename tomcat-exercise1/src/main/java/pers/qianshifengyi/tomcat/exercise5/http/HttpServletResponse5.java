package pers.qianshifengyi.tomcat.exercise5.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Created by zhangshan on 17/5/18.
 */
public class HttpServletResponse5 implements ServletResponse {

    private String characterEncoding = "UTF-8";

    private OutputStream outputStream;

    private PrintWriter printWriter;

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;

    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    @Override
    public String getCharacterEncoding() {
        return characterEncoding;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        System.out.println("enter HttpServletResponse5 getWriter");


        if(printWriter == null){
            printWriter = new PrintWriter(outputStream,true);
        }
        return printWriter;

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
