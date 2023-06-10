package com.github.whatasame.notisy.tistory.service;

import com.github.whatasame.notisy.tistory.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryServiceTest {

    CategoryService categoryService;

    @BeforeEach
    void beforeEach() {
        categoryService = new CategoryService(new CategoryRepository());
    }

    @Test
    @DisplayName("[카테고리 ID 가져오기] 특정 이름을 갖는 카테고리 아이디 정보를 가져온다.")
    void test_01() {
        /* given */
        String blogName = System.getenv("NOTISY_BLOG_NAME");
        String categoryName = "Test category"; // Your category name

        Assertions.assertNotNull(blogName, "블로그 이름 환경 변수를 확인하세요");

        /* when */
        String categoryId = categoryService.getCategoryId(blogName, categoryName);

        /* then */
        Assertions.assertNotNull(categoryId);
    }

}