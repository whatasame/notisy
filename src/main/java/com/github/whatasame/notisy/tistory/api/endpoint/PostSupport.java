package com.github.whatasame.notisy.tistory.api.endpoint;

import com.github.whatasame.notisy.tistory.api.exception.TistoryException;
import com.github.whatasame.notisy.tistory.api.http.TistoryHttpClient;
import com.github.whatasame.notisy.tistory.api.http.TistoryHttpResponse;
import com.github.whatasame.notisy.tistory.api.json.TistoryJsonSerializer;
import com.github.whatasame.notisy.tistory.api.model.TistoryResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface PostSupport extends EndpointsSupport {

    /**
     * API reference
     * <p>
     * https://tistory.github.io/document-tistory-apis/apis/v1/post/write.html
     */
    default String writePost(
            TistoryHttpClient httpClient,
            TistoryJsonSerializer jsonSerializer,
            String baseUrl, Map<String,
            List<String>> params
    ) {
        TistoryHttpResponse httpResponse =
                httpClient.post(
                        baseUrl + "/post/write",
                        buildRequestParams(params),
                        Collections.emptyMap()
                );

        if (httpResponse.status() == 200) {
            TistoryResponse tistoryResponse = jsonSerializer.toTistoryResponse(httpResponse.body());
            return tistoryResponse.tistory().postId();
        } else {
            throw new TistoryException("포스트 작성 실패: " + httpResponse.body());
        }
    }
}
