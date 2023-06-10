package com.github.whatasame.notisy.gui.config;

import com.github.whatasame.notisy.key.KeyManager;
import com.github.whatasame.notisy.notion.repository.BlockRepository;
import com.github.whatasame.notisy.notion.repository.DatabaseRepository;
import com.github.whatasame.notisy.notion.repository.PageRepository;
import com.github.whatasame.notisy.notion.service.BlockService;
import com.github.whatasame.notisy.notion.service.DatabaseService;
import com.github.whatasame.notisy.notion.service.PageService;
import com.github.whatasame.notisy.tistory.repository.BlogRepository;
import com.github.whatasame.notisy.tistory.repository.CategoryRepository;
import com.github.whatasame.notisy.tistory.repository.PostRepository;
import com.github.whatasame.notisy.tistory.service.BlogService;
import com.github.whatasame.notisy.tistory.service.CategoryService;
import com.github.whatasame.notisy.tistory.service.PostService;

public class AppConfig {

    private KeyManager keyManager;

    public KeyManager keyManager() {
        if (keyManager == null) {
            keyManager = new KeyManager();
        }

        return keyManager;
    }

    // ------------------------------
    //  Tistory layer
    // ------------------------------

    public BlogService blogService() {
        return new BlogService(blogRepository());
    }

    public BlogRepository blogRepository() {
        return new BlogRepository();
    }

    public CategoryService categoryService() {
        return new CategoryService(categoryRepository());
    }

    public CategoryRepository categoryRepository() {
        return new CategoryRepository();
    }

    public PostService postService() {
        return new PostService(postRepository(), blockService(), categoryService(), keyManager(), pageService());
    }

    public PostRepository postRepository() {
        return new PostRepository();
    }


    // ------------------------------
    //  Notion layer
    // ------------------------------

    public DatabaseService databaseService() {
        return new DatabaseService(notionDatabaseRepository());
    }

    public DatabaseRepository notionDatabaseRepository() {
        return new DatabaseRepository();
    }

    public PageService pageService() {
        return new PageService(pageRepository());
    }

    public PageRepository pageRepository() {
        return new PageRepository();
    }

    public BlockService blockService() {
        return new BlockService(blockRepository());
    }

    public BlockRepository blockRepository() {
        return new BlockRepository();
    }
}
