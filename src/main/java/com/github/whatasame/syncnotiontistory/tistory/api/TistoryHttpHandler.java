package com.github.whatasame.syncnotiontistory.tistory.api;

import com.github.whatasame.syncnotiontistory.key.Key;
import com.github.whatasame.syncnotiontistory.key.KeyManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.Objects;

public class TistoryHttpHandler {

    enum EndPoints {
        BLOG_INFO("https://www.tistory.com/apis/blog/info");

        final String URL;

        EndPoints(String url) {
            this.URL = url;
        }

    }

    private final String ACCESS_TOKEN;
    private final Gson gson = new Gson();
    private final OkHttpClient client = new OkHttpClient();

    public TistoryHttpHandler() {
        this.ACCESS_TOKEN = new KeyManager().getKey(Key.TISTORY_ACCESS_TOKEN.getName());
    }

    private JsonObject getItem(Response response) throws IOException {
        return gson.fromJson(
                        Objects.requireNonNull(response.body()).string(),
                        JsonObject.class
                )
                .getAsJsonObject("tistory")
                .getAsJsonObject("item");
    }

    private Response getResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (response.code() != 200) {
            throw new HttpRetryException("정상 접근이 아님", response.code());
        }

        return response;
    }

    public JsonArray getBlogInfo() throws IOException {
        String getParamsUrl = Objects.requireNonNull(HttpUrl.parse(EndPoints.BLOG_INFO.URL))
                .newBuilder()
                .addQueryParameter("access_token", ACCESS_TOKEN)
                .addQueryParameter("output", "json")
                .toString();

        Response response = getResponse(getParamsUrl);

        return getItem(response)
                .getAsJsonArray("blogs");
    }

}
