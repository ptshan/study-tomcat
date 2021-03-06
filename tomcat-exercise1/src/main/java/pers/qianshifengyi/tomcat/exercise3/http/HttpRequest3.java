package pers.qianshifengyi.tomcat.exercise3.http;

import pers.qianshifengyi.tomcat.exercise3.HttpRequest3ServletInputStream;
import pers.qianshifengyi.tomcat.util.Enumerator;
import pers.qianshifengyi.tomcat.util.ParameterMap;
import pers.qianshifengyi.tomcat.util.RequestUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

/**
 * Created by zhangshan on 17/5/4.
 */
public class HttpRequest3 implements HttpServletRequest {

    private InputStream is;

    private String contentType;
    private int contentLength;

    private String method;
    private String protocol;
    private String requestURI;

    private List<Cookie> cookies = new ArrayList<Cookie>();

    private Map<String,ArrayList<String>> headers = new HashMap<String,ArrayList<String>>();

    private boolean requestedSessionIdFromCookie;
    private String requestedSessionId;
    private boolean requestedSessionURL;

    private String queryString;

    private boolean parsed = false;

    private String characterEncoding="UTF-8";

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public synchronized void addCookie(Cookie cookie) {

        this.cookies.add(cookie);
    }

    public void setRequestedSessionIdFromCookie(boolean requestedSessionIdFromCookie) {
        this.requestedSessionIdFromCookie = requestedSessionIdFromCookie;
    }

    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    public boolean isRequestedSessionURL() {
        return requestedSessionURL;
    }

    public void setRequestedSessionURL(boolean requestedSessionURL) {
        this.requestedSessionURL = requestedSessionURL;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void addHeader(String name, String value){
        //name = name.toLowerCase();
        synchronized (headers){
            ArrayList<String> values = headers.get(name);
            if(values == null){
                values = new ArrayList<String>();
                headers.put(name,values);
            }
            values.add(value);
        }
    }

    public HttpRequest3(InputStream is){
        this.is = is;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    @Override
    public String getAuthType() {
        return null;
    }

    @Override
    public Cookie[] getCookies() {
        return (Cookie[])cookies.toArray();
    }

    @Override
    public long getDateHeader(String s) {
        return 0;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public Enumeration getHeaders(String s) {
        return null;
    }

    @Override
    public Enumeration getHeaderNames() {
        return null;
    }

    @Override
    public int getIntHeader(String s) {
        return 0;
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public String getPathInfo() {
        return null;
    }

    @Override
    public String getPathTranslated() {
        return null;
    }

    @Override
    public String getContextPath() {
        return null;
    }

    @Override
    public String getQueryString() {
        return null;
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return null;
    }

    @Override
    public String getRequestURI() {
        return null;
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public HttpSession getSession(boolean b) {
        return null;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return requestedSessionIdFromCookie;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new HttpRequest3ServletInputStream(this);
    }

    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public Enumeration getParameterNames() {
        Map paramsMap = getParameterMap();
        Set keys = paramsMap.keySet();
        return new Enumerator(keys);
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map getParameterMap() {
        ParameterMap results = new ParameterMap();
        results.setLocked(false);
        if(characterEncoding == null){
            characterEncoding = "UTF-8";
        }

        // 从URL获取请求参数
        String queryStr = getQueryString();
        try {
            RequestUtil.parseParameters(results,queryStr,characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 从请求体获取参数
        String contentTypeStr = getContentType();
        if(contentTypeStr == null){
            contentTypeStr = "";
        }
        contentTypeStr = contentTypeStr.trim();
        if(contentTypeStr.contains(";")){
            contentTypeStr = contentTypeStr.substring(0,contentTypeStr.indexOf(";"));
        }
        if("POST".equals(getMethod()) && getContentLength() >0 && contentTypeStr.equals("application/x-www-form-urlencoded")){
            byte[] buf = new byte[getContentLength()];
            try {
                ServletInputStream sis = getInputStream();
                int len = 0;
                // 读取数据到buf中,目的是用于下面的转换
                while(len < getContentLength()){
                    int i = sis.read(buf,len,getContentLength()-len);
                    if(i < 0){
                        break;
                    }
                    len = len + i;
                }
                // if(len < getContentLength())
                if(len != getContentLength()){
                    throw new IllegalStateException("len and contentLength dismatch");
                }

                RequestUtil.parseParameters(results,buf,characterEncoding);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        results.setLocked(true);
        parsed = true;

        return results;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

}
