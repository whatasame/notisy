package com.github.whatasame.notisy.tistory.api.json;

import com.github.whatasame.notisy.tistory.api.model.TistoryResponse;
import com.google.gson.Gson;

public class TistoryGsonSerializer implements TistoryJsonSerializer {

    private final Gson gson;

    public TistoryGsonSerializer() {
        gson = new Gson();
    }

    @Override
    public TistoryResponse toTistoryResponse(String body) {
        return gson.fromJson(body, TistoryResponse.class);
    }
}
