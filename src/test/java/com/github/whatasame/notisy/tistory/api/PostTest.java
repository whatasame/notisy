package com.github.whatasame.notisy.tistory.api;

import com.github.whatasame.notisy.tistory.TistoryClientManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostTest {

    TistoryClient tistoryClient;

    @BeforeEach
    void beforeEach() {
        tistoryClient = TistoryClientManager.getClient();
    }

    @Test
    @DisplayName("[글 작성하기] HTML로 작성된 글을 업로드한다.")
    void test_01() {
        /* given */
        String blogName = System.getenv("NOTISY_BLOG_NAME");
        String title = "글 쓰기 테스트";
        String content = "<h2>Notisy 글 작성 테스트입니다.<h2><p>Hello Notisy!</p>";
        String categoryId = System.getenv("NOTISY_CATEGORY_ID");
        Assertions.assertNotNull(blogName, "블로그 이름 환경 변수를 확인해주세요");
        Assertions.assertNotNull(categoryId, "카테고리 아이디 환경 변수를 확인해주세요");

        /* when */
        String postId = tistoryClient.writePost(blogName, categoryId, title, content);

        /* then */
        Assertions.assertNotNull(postId);
    }

    @Test
    @DisplayName("[글 수정하기] HTML로 작성된 글을 수정한다.")
    void test_02() {
        /* given */
        String blogName = System.getenv("NOTISY_BLOG_NAME");
        String title = "글 수정 테스트";
        String content = "<h2>Notisy 글 수정 테스트입니다.<h2><p>World Notisy!</p>";
        String categoryId = System.getenv("NOTISY_CATEGORY_ID");
        String postId = "1";

        Assertions.assertNotNull(blogName, "블로그 이름 환경 변수를 확인해주세요");
        Assertions.assertNotNull(categoryId, "카테고리 아이디 환경 변수를 확인해주세요");

        /* when */
        String editedPostId = tistoryClient.modifyPost(blogName, title, content, categoryId, postId);


        /* then */
        Assertions.assertEquals(postId, editedPostId);
    }
}
