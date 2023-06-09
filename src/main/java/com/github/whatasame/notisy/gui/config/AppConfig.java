package com.github.whatasame.notisy.gui.config;

import com.github.whatasame.notisy.key.KeyManager;
import com.github.whatasame.notisy.notion.repository.DatabaseRepository;
import com.github.whatasame.notisy.notion.service.DatabaseService;
import com.github.whatasame.notisy.tistory.repository.BlogRepository;
import com.github.whatasame.notisy.tistory.service.BlogService;

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

    // ------------------------------
    //  Notion layer
    // ------------------------------

    public DatabaseService databaseService() {
        return new DatabaseService(notionDatabaseRepository());
    }

    public DatabaseRepository notionDatabaseRepository() {
        return new DatabaseRepository();
    }

}
