package com.jdbc.ftl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@SuppressWarnings("Deprecated")
public class ConnectToCouch {

    HttpClient client = new DefaultHttpClient();
    ResponseHandler<String> handler = new BasicResponseHandler();

    public String getHttpResponse(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        String body = client.execute(httpGet, handler);
        return body;
    }
}
