package com.github.whatasame.syncnotiontistory.tistory.api;

import com.github.whatasame.syncnotiontistory.key.Key;
import com.github.whatasame.syncnotiontistory.key.KeyManager;
import com.github.whatasame.syncnotiontistory.tistory.model.TistoryBlog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.HttpRetryException;

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
    private final OkHttpClient okHttpClient = new OkHttpClient();

    public TistoryHttpHandler() {
        this.ACCESS_TOKEN = new KeyManager().getKey(Key.TISTORY_ACCESS_TOKEN.getName());
    }

    private JsonObject sendRequest(Request request) throws IOException {
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new HttpRetryException(response.message(), response.code());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, JsonObject.class);
        }
    }

    public TistoryBlog getDefaultBlog() throws IOException {
        String httpUrl = HttpUrl.parse(EndPoints.BLOG_INFO.URL)
                .newBuilder()
                .addQueryParameter("access_token", ACCESS_TOKEN)
                .addQueryParameter("output", "json")
                .toString();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        JsonArray blogs = sendRequest(request)
                .getAsJsonObject("tistory")
                .getAsJsonObject("item")
                .getAsJsonArray("blogs");

        for (JsonElement element : blogs) {
            if (element instanceof JsonObject) {
                JsonObject blog = (JsonObject) element;
                if (blog.get("default").getAsString().equals("Y")) {
                    return gson.fromJson(blog, TistoryBlog.class);
                }
            }
        }

        return null;
    }

}
