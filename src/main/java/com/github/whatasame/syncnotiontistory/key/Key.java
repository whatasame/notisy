package com.github.whatasame.syncnotiontistory.key;

public enum Key {
    TISTORY_ACCESS_TOKEN("TISTORY_ACCESS_TOKEN");

    private final String NAME;

    Key(String name) {
        this.NAME = name;
    }

    public String getName() {
        return this.NAME;
    }
}
