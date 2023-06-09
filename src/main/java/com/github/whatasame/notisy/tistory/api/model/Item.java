package com.github.whatasame.notisy.tistory.api.model;

import java.util.List;

public record Item(
        String id,
        String userId,
        List<Blog> blogs
) {

}
