package com.github.whatasame.notisy.tistory.api;

import com.github.whatasame.notisy.tistory.TistoryClientManager;
import com.github.whatasame.notisy.tistory.api.model.Blog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BlogTest {

    TistoryClient tistoryClient;

    @BeforeEach
    void beforeEach() {
        tistoryClient = TistoryClientManager.getClient();
    }

    @Test
    @DisplayName("[블로그 정보 가져오기] 사용자가 개설한 모든 블로그 정보를 가져온다.")
    void test_01() {
        /* given */


        /* when */
        List<Blog> blogs = tistoryClient.retrieveBlogs();

        /* then */
        Assertions.assertFalse(blogs.isEmpty());
    }

}
