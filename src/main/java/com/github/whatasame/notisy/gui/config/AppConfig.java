package com.github.whatasame.notisy.gui.config;

import com.github.whatasame.notisy.key.KeyManager;
import com.github.whatasame.notisy.notion.repository.DatabaseRepository;
import com.github.whatasame.notisy.notion.service.DatabaseService;

public class AppConfig {

    private KeyManager keyManager;

    public DatabaseService databaseService() {
        return new DatabaseService(notionDatabaseRepository());
    }

    public DatabaseRepository notionDatabaseRepository() {
        return new DatabaseRepository();
    }

    public KeyManager keyManager() {
        if (keyManager == null) {
            keyManager = new KeyManager();
        }

        return keyManager;
    }

}
