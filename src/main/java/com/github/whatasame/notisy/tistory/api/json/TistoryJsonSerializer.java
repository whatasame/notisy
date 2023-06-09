package com.github.whatasame.notisy.tistory.api.json;

import com.github.whatasame.notisy.tistory.api.model.TistoryResponse;

public interface TistoryJsonSerializer {

    TistoryResponse toTistoryResponse(String body);

}
