package com.github.whatasame.notisy.tistory.repository;

import com.github.whatasame.notisy.tistory.TistoryClientManager;
import com.github.whatasame.notisy.tistory.api.TistoryClient;
import com.github.whatasame.notisy.tistory.api.model.Category;

import java.util.List;

public class CategoryRepository {

    public List<Category> findAll(String blogName) {
        TistoryClient client = TistoryClientManager.getClient();

        return client.retrieveCategories(blogName);
    }

}
