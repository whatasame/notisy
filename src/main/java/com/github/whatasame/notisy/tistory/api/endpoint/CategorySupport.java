package com.github.whatasame.notisy.tistory.api.endpoint;

import com.github.whatasame.notisy.tistory.api.exception.TistoryException;
import com.github.whatasame.notisy.tistory.api.http.TistoryHttpClient;
import com.github.whatasame.notisy.tistory.api.http.TistoryHttpResponse;
import com.github.whatasame.notisy.tistory.api.json.TistoryJsonSerializer;
import com.github.whatasame.notisy.tistory.api.model.Category;
import com.github.whatasame.notisy.tistory.api.model.TistoryResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * API reference
 * <p>
 * https://tistory.github.io/document-tistory-apis/apis/v1/category/list.html
 */
public interface CategorySupport extends EndpointsSupport {

    default List<Category> retrieveCategories(TistoryHttpClient httpClient, TistoryJsonSerializer jsonSerializer, String baseUrl, Map<String, List<String>> params) {
        TistoryHttpResponse httpResponse =
                httpClient.get(
                        baseUrl + "/category/list",
                        buildRequestParams(params),
                        Collections.emptyMap()
                );

        if (httpResponse.status() == 200) {
            TistoryResponse tistoryResponse = jsonSerializer.toTistoryResponse(httpResponse.body());
            return tistoryResponse.tistory().item().categories();
        } else {
            throw new TistoryException("블로그 조회 실패");
        }
    }

}