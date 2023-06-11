package com.github.whatasame.notisy.tistory.api;

import com.github.whatasame.notisy.tistory.api.endpoint.BlogSupport;
import com.github.whatasame.notisy.tistory.api.endpoint.CategorySupport;
import com.github.whatasame.notisy.tistory.api.endpoint.PostSupport;
import com.github.whatasame.notisy.tistory.api.http.HttpUrlConnTistoryHttpClient;
import com.github.whatasame.notisy.tistory.api.http.TistoryHttpClient;
import com.github.whatasame.notisy.tistory.api.json.TistoryGsonSerializer;
import com.github.whatasame.notisy.tistory.api.json.TistoryJsonSerializer;
import com.github.whatasame.notisy.tistory.api.model.Blog;
import com.github.whatasame.notisy.tistory.api.model.Category;

import java.util.List;
import java.util.Map;

public class TistoryClient implements BlogSupport, CategorySupport, PostSupport {

    private static final String defaultBaseUrl = "https://www.tistory.com/apis";
    private static final TistoryHttpClient defaultHttpClient = new HttpUrlConnTistoryHttpClient();
    private static final TistoryJsonSerializer defaultJsonSerializer = new TistoryGsonSerializer();

    private final String token;

    public TistoryClient(String token) {
        this.token = token;
    }

    public List<Blog> retrieveBlogs() {
        return retrieveBlogs(
                defaultHttpClient,
                defaultJsonSerializer,
                defaultBaseUrl,
                Map.of("access_token", List.of(token))
        );
    }

    public List<Category> retrieveCategories(String blogName) {
        return retrieveCategories(
                defaultHttpClient,
                defaultJsonSerializer,
                defaultBaseUrl,
                Map.of(
                        "access_token", List.of(token),
                        "blogName", List.of(blogName)
                )
        );
    }

    public String writePost(String blogName, String title, String content, String categoryId) {
        return writePost(
                defaultHttpClient,
                defaultJsonSerializer,
                defaultBaseUrl,
                Map.of(
                        "access_token", List.of(token),
                        "blogName", List.of(blogName),
                        "title", List.of(title),
                        "content", List.of(content),
                        "category", List.of(categoryId)
                )
        );
    }

    public String modifyPost(String blogName, String title, String content, String categoryId, String postId) {
        return modifyPost(
                defaultHttpClient,
                defaultJsonSerializer,
                defaultBaseUrl,
                Map.of(
                        "access_token", List.of(token),
                        "blogName", List.of(blogName),
                        "postId", List.of(postId),
                        "title", List.of(title),
                        "content", List.of(content),
                        "category", List.of(categoryId)
                )
        );
    }
}
