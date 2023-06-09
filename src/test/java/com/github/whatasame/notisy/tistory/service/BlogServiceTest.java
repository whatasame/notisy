package com.github.whatasame.notisy.tistory.service;

import com.github.whatasame.notisy.tistory.api.model.Blog;
import com.github.whatasame.notisy.tistory.repository.BlogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class BlogServiceTest {

    BlogService blogService = new BlogService(new BlogRepository());


    @Test
    @DisplayName("[대표 블로그 정보 가져오기] 대표 블로그 정보를 하나 가져온다.")
    void test_01() {
        /* given */


        /* when */
        Optional<Blog> defaultBlog = blogService.getDefaultBlog();


        /* then */
        Assertions.assertTrue(defaultBlog.isPresent());
    }

}