package com.github.whatasame.notisy.key;

public enum Key {
    TISTORY_ACCESS_TOKEN("TISTORY_ACCESS_TOKEN"),
    NOTION_TOKEN("NOTION_TOKEN"),
    DATABASE_NAME("DATABASE_NAME");

    private final String NAME;

    Key(String name) {
        this.NAME = name;
    }

    public String getName() {
        return this.NAME;
    }
}
