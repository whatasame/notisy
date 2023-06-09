package com.github.whatasame.notisy.tistory.api.endpoint;

import com.github.whatasame.notisy.tistory.api.exception.TistoryException;
import com.github.whatasame.notisy.tistory.api.http.TistoryHttpClient;
import com.github.whatasame.notisy.tistory.api.http.TistoryHttpResponse;
import com.github.whatasame.notisy.tistory.api.json.TistoryJsonSerializer;
import com.github.whatasame.notisy.tistory.api.model.Blog;
import com.github.whatasame.notisy.tistory.api.model.TistoryResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * API reference
 * https://tistory.github.io/document-tistory-apis/apis/v1/blog/list.html
 */
public interface BlogSupport extends EndpointsSupport {

    default List<Blog> retrieveBlogs(TistoryHttpClient httpClient, TistoryJsonSerializer jsonSerializersonSerializer, String baseUrl, Map<String, List<String>> params) {
        TistoryHttpResponse httpResponse =
                httpClient.get(
                        baseUrl + "/blog/info",
                        buildRequestParams(params),
                        Collections.emptyMap()
                );

        if (httpResponse.status() == 200) {
            TistoryResponse tistoryResponse = jsonSerializersonSerializer.toTistoryResponse(httpResponse.body());
            return tistoryResponse.tistory().item().blogs();
        } else {
            throw new TistoryException("블로그 조회 실패");
        }

    }

}
