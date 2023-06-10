package com.github.whatasame.notisy.tistory.service;

import com.github.whatasame.notisy.tistory.api.exception.TistoryException;
import com.github.whatasame.notisy.tistory.repository.CategoryRepository;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String getCategoryId(String blogName, String categoryName) {
        return categoryRepository.findAll(blogName).stream()
                .filter(category -> category.name().equals(categoryName))
                .findAny()
                .orElseThrow(() -> new TistoryException("카테고리를 찾을 수 없습니다."))
                .id();
    }
}
