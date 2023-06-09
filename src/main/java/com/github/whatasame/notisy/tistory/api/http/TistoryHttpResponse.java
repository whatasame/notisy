package com.github.whatasame.notisy.tistory.api.http;

import java.util.List;
import java.util.Map;

public record TistoryHttpResponse(
        int status,
        String body,
        Map<String, List<String>> headers
) {
}
