package com.foody.order.http;

import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Data
public class HttpRequest {

    private URL url;

    private byte[] payload;

    private HttpMethod method;

    private String contentType;

    private Map<String, String> headers;

    private int connectionTimeOut;


    public HttpRequest(String url, HttpMethod method) throws MalformedURLException {
        this.setUrl(url);
        this.method = method;
    }



    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }


    public void setUrl(URL url) {
        this.url = url;
    }


    public void setPayload(String payload) {
        this.setPayload(payload == null ? null : payload.getBytes());
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }



    public void addHeader(String key, String value) {

        if (this.headers == null)
            this.headers = new HashMap<>();

        this.headers.put(key, value);
    }


    public String getHeaderValue(String key) {

        return this.headers == null ? null : this.headers.get(key);
    }
}
