package pers.qianshifengyi.tomcat.exercise5.http;

import org.apache.catalina.net.ServerSocketFactory;
import pers.qianshifengyi.tomcat.util.Constants;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by zhangshan on 17/5/18.
 */
public class ServerSocketFactoryImpl implements ServerSocketFactory {

    @Override
    public ServerSocket createSocket(int port) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        return createSocket(port,1);
    }

    @Override
    public ServerSocket createSocket(int port, int backlog) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        return createSocket(port,backlog,InetAddress.getByName(Constants.SERVER_IP));
    }

    @Override
    public ServerSocket createSocket(int port, int backlog, InetAddress ifAddress) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        return new ServerSocket(port,backlog,ifAddress);
    }
}
