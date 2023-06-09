package com.github.whatasame.notisy.tistory.api.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API reference
 * <p>
 * https://tistory.github.io/document-tistory-apis/apis/
 */
public interface EndpointsSupport {

    default Map<String, List<String>> buildRequestParams(Map<String, List<String>> additionalOnes) {
        Map<String, List<String>> defaultParams = new HashMap<>();
        defaultParams.put("output", List.of("json"));

        defaultParams.putAll(additionalOnes);
        return defaultParams;
    }

}

