package com.github.whatasame.notisy.tistory.repository;

import com.github.whatasame.notisy.tistory.TistoryClientManager;
import com.github.whatasame.notisy.tistory.api.TistoryClient;
import com.github.whatasame.notisy.tistory.api.model.Blog;

import java.util.List;

public class BlogRepository {

    public List<Blog> findAll() {
        TistoryClient client = TistoryClientManager.getClient();

        return client.retrieveBlogs();
    }


}
