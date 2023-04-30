package com.github.whatasame.syncnotiontistory.tistory.api;

import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TistoryHttpHandlerTest {

    TistoryHttpHandler tistoryHttpHandler;

    @BeforeEach
    void beforeEach() {
        tistoryHttpHandler = new TistoryHttpHandler();
    }

    @Test
    void 유효한_키로_블로그_정보_가져오기() {
        JsonArray blogs = assertDoesNotThrow(tistoryHttpHandler::getBlogInfo);

        assertFalse(blogs.isEmpty());
    }

}