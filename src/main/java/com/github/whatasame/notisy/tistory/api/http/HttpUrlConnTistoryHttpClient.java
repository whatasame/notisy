package com.github.whatasame.notisy.tistory.api.http;

import com.github.whatasame.notisy.tistory.api.exception.TistoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpUrlConnTistoryHttpClient implements TistoryHttpClient {

    private final int connectTimeoutMillis = 10_000;
    private final int readTimeoutMillis = 30_000;

    public HttpUrlConnTistoryHttpClient() {
    }

    @Override
    public TistoryHttpResponse get(String url, Map<String, List<String>> query, Map<String, String> headers) {
        String q = buildQueryString(query);
        String fullUrl = buildFullUrl(url, q);
        HttpURLConnection conn = buildConnectionObject(fullUrl, headers);
        try {
            conn.setRequestMethod("GET");
            try (InputStream input = connect(conn)) {
                return new TistoryHttpResponse(
                        conn.getResponseCode(),
                        readResponseBody(input),
                        conn.getHeaderFields()
                );
            }
        } catch (Exception e) {
            throw new TistoryException(e.getMessage());
        } finally {
            disconnect(conn);
        }
    }

    /**
     * Post 요청이지만 Tistory API에서는 body를 넣지 않고 param에서 모든 것을 해결한다.
     */
    @Override
    public TistoryHttpResponse post(String url, Map<String, List<String>> query, Map<String, String> headers) {
        String q = buildQueryString(query);
        String fullUrl = buildFullUrl(url, q);
        HttpURLConnection conn = buildConnectionObject(fullUrl, headers);
        try {
            conn.setRequestMethod("POST"); // HTTP Method만 다르다.
            try (InputStream input = connect(conn)) {
                return new TistoryHttpResponse(
                        conn.getResponseCode(),
                        readResponseBody(input),
                        conn.getHeaderFields()
                );
            }
        } catch (Exception e) {
            throw new TistoryException(e.getMessage());
        } finally {
            disconnect(conn);
        }
    }

    private HttpURLConnection buildConnectionObject(String fullUrl, Map<String, String> headers) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(fullUrl).openConnection();
            conn.setRequestProperty("Connection", "close");
            conn.setConnectTimeout(connectTimeoutMillis);
            conn.setReadTimeout(readTimeoutMillis);
            headers.forEach(conn::setRequestProperty);

            return conn;
        } catch (IOException e) {
            throw new TistoryException(e.getMessage());
        }
    }

    private InputStream connect(HttpURLConnection conn) {
        try {
            conn.connect();
            return conn.getInputStream();
        } catch (IOException e) {
            return conn.getErrorStream();
        }
    }

    private String readResponseBody(InputStream input) {
        if (Objects.isNull(input)) {
            return "";
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new TistoryException(e.getMessage());
        }
    }

    private void disconnect(HttpURLConnection conn) {
        try {
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
