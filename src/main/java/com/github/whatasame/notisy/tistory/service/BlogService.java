package com.github.whatasame.notisy.tistory.service;

import com.github.whatasame.notisy.tistory.api.model.Blog;
import com.github.whatasame.notisy.tistory.repository.BlogRepository;

import java.util.List;
import java.util.Optional;

public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
    public Optional<Blog> getDefaultBlog() {
        List<Blog> blogs = blogRepository.findAll();

        return blogs.stream()
                .filter(blog -> blog.isDefault().equals("Y"))
                .findAny();
    }
}
