package com.github.whatasame.syncnotiontistory.key;

public enum Key {
    TISTORY_ACCESS_TOKEN("TISTORY_ACCESS_TOKEN");

    private final String name;

    Key(String name) {
        this.name = name;
    }

    public String getKeyName() {
        return this.name;
    }
}
