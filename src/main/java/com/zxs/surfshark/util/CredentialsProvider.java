package com.zxs.surfshark.util;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class CredentialsProvider {
    public void CredentialsProvider() throws IOException {
        final String PROXY_HOST = "2.58.241.45";
        final int PROXY_PORT = 38011;
        final String PROXY_USERNAME = "YJnXy5ycMmJhbkNh8RS7QERy";
        final String PROXY_PASSWORD = "kQy5aEegUX2kEu4HEq5EYKqs";
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(PROXY_HOST, PROXY_PORT),
                new UsernamePasswordCredentials(PROXY_USERNAME, PROXY_PASSWORD));

        CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

        HttpHost proxy = new HttpHost(PROXY_HOST, PROXY_PORT);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        HttpPost req = new HttpPost(PROXY_HOST);// GET请求用HttpGet
        req.setConfig(config);
        CloseableHttpResponse resp = client.execute(req);
        try {
            System.out.println(resp);
        } finally {
            resp.close();
        }
    }
}
