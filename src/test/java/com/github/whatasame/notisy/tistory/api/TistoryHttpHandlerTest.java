package com.github.whatasame.notisy.tistory.api;

import com.github.whatasame.notisy.tistory.model.TistoryBlog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TistoryHttpHandlerTest {

    TistoryHttpHandler tistoryHttpHandler;

    @BeforeEach
    void beforeEach() {
        tistoryHttpHandler = new TistoryHttpHandler();
    }

    @Test
    void 대표_블로그_정보_가져오기() throws IOException {
        TistoryBlog defaultBlog = tistoryHttpHandler.getDefaultBlog();

        Assertions.assertNotNull(defaultBlog);

        System.out.println(defaultBlog);
    }
}