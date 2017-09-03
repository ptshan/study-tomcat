package pers.qianshifengyi.undertow.test;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * Created by mountain on 2017/9/2.
 */
public class HelloWorldServer {

    /**
     * http://localhost:8081/
     * @param args
     */
    public static void main(String[] args) {
        Undertow undertow = Undertow.builder()
                .addHttpListener(8081,"localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
                        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE,"text/plain");
                        httpServerExchange.getResponseSender().send("ye ye ,come on baby");
                    }
                }).build();

        undertow.start();


    }



}
