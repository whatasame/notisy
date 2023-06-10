package com.github.whatasame.notisy.tistory.api.model;

public record Category(
        String id,
        String name,
        String parent,
        String label,
        String entries
) {
}
