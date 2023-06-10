package com.github.whatasame.notisy.tistory.api.http;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public interface TistoryHttpClient {

    TistoryHttpResponse get(String url, Map<String, List<String>> query, Map<String, String> headers);

    TistoryHttpResponse post(String url, Map<String, List<String>> query, Map<String, String> headers);

    default String urlEncode(String value) {
        return URLEncoder.encode(value, UTF_8);
    }

    default String buildQueryString(Map<String, List<String>> query) {
        return query.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(value ->
                                urlEncode(entry.getKey()) + "=" + urlEncode(value)
                        )
                )
                .collect(Collectors.joining("&", "?", ""));
    }

    default String buildFullUrl(String url, String q) {
        return url + (q.equals("?") ? "" : q);
    }

}
