package com.github.whatasame.notisy.tistory.api.model;

public record ResponseData(
        String status,
        Item item,

        // -----------------
        //  Post
        // -----------------

        String postId,
        String url
) {
}
