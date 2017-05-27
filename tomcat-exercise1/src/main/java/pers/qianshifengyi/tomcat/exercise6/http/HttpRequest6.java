package pers.qianshifengyi.tomcat.exercise6.http;

import org.apache.catalina.*;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by zhangshan on 17/5/17.
 */
public class HttpRequest6 implements HttpRequest {

    private ServletRequest servletRequest;

    private String requestURI;

    private String decodedRequestURI;

    private String contextPath;

    private String protocol;

    public String getContextPath() {
        return contextPath;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setServletRequest(ServletRequest servletRequest){
        this.servletRequest = servletRequest;
    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public void addHeader(String name, String value) {

    }

    @Override
    public void addLocale(Locale locale) {

    }

    @Override
    public void addParameter(String name, String[] values) {

    }

    @Override
    public void clearCookies() {

    }

    @Override
    public void clearHeaders() {

    }

    @Override
    public void clearLocales() {

    }

    @Override
    public void clearParameters() {

    }

    @Override
    public void setAuthType(String type) {

    }

    @Override
    public void setContextPath(String path) {
        contextPath = path;
    }

    @Override
    public void setMethod(String method) {

    }

    @Override
    public void setQueryString(String query) {

    }

    @Override
    public void setPathInfo(String path) {

    }

    @Override
    public void setRequestedSessionCookie(boolean flag) {

    }

    @Override
    public void setRequestedSessionId(String id) {

    }

    @Override
    public void setRequestedSessionURL(boolean flag) {

    }

    @Override
    public void setRequestURI(String uri) {
        requestURI = uri;
    }

    @Override
    public void setDecodedRequestURI(String uri) {
        decodedRequestURI = uri;
    }

    @Override
    public String getDecodedRequestURI() {
        return decodedRequestURI;
    }

    @Override
    public void setServletPath(String path) {

    }

    @Override
    public void setUserPrincipal(Principal principal) {

    }

    @Override
    public String getAuthorization() {
        return null;
    }

    @Override
    public void setAuthorization(String authorization) {

    }

    @Override
    public Connector getConnector() {
        return null;
    }

    @Override
    public void setConnector(Connector connector) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public ServletRequest getRequest() {
        return servletRequest;
    }

    @Override
    public Response getResponse() {
        return null;
    }

    @Override
    public void setResponse(Response response) {

    }

    @Override
    public Socket getSocket() {
        return null;
    }

    @Override
    public void setSocket(Socket socket) {

    }

    @Override
    public InputStream getStream() {
        return null;
    }

    @Override
    public void setStream(InputStream stream) {

    }

    @Override
    public Wrapper getWrapper() {
        return null;
    }

    @Override
    public void setWrapper(Wrapper wrapper) {

    }

    @Override
    public ServletInputStream createInputStream() throws IOException {
        return null;
    }

    @Override
    public void finishRequest() throws IOException {

    }

    @Override
    public Object getNote(String name) {
        return null;
    }

    @Override
    public Iterator getNoteNames() {
        return null;
    }

    @Override
    public void recycle() {

    }

    @Override
    public void removeNote(String name) {

    }

    @Override
    public void setContentLength(int length) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setNote(String name, Object value) {

    }

    @Override
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public void setRemoteAddr(String remote) {
    }

    @Override
    public void setScheme(String scheme) {

    }

    @Override
    public void setSecure(boolean secure) {

    }

    @Override
    public void setServerName(String name) {

    }

    @Override
    public void setServerPort(int port) {

    }
}
