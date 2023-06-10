package com.github.whatasame.notisy.tistory.repository;

import com.github.whatasame.notisy.tistory.TistoryClientManager;
import com.github.whatasame.notisy.tistory.api.TistoryClient;

public class PostRepository {

    public String writePost(String blogName, String title, String content, String categoryId) {
        TistoryClient client = TistoryClientManager.getClient();

        return client.writePost(blogName, title, content, categoryId);
    }

    public String modifyPost(String blogName, String title, String content, String categoryId, String postId) {
        TistoryClient client = TistoryClientManager.getClient();

        return client.modifyPost(blogName, title, content, categoryId, postId);
    }

}
