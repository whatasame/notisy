package com.github.whatasame.notisy.tistory.api;

import com.github.whatasame.notisy.tistory.TistoryClientManager;
import com.github.whatasame.notisy.tistory.api.model.Blog;
import com.github.whatasame.notisy.tistory.api.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class CategoryTest {

    TistoryClient tistoryClient;

    @BeforeEach
    void beforeEach() {
        tistoryClient = TistoryClientManager.getClient();
    }

    @Test
    @DisplayName("[카테고리 정보 가져오기] 사용자가 생성한 모든 카테고리 정보를 가져온다.")
    void test_01() {
        /* given */
        List<Blog> blogs = tistoryClient.retrieveBlogs();
        Optional<Blog> blog = blogs.stream()
                .filter(b -> b.isDefault().equals("Y"))
                .findAny();
        Assertions.assertTrue(blog.isPresent());
        Blog mainBlog = blog.get();

        /* when */
        String blogName = mainBlog.name();
        List<Category> categories = tistoryClient.retrieveCategories(blogName);

        /* then */
        Assertions.assertFalse(categories.isEmpty());
    }

}
