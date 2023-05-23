package com.github.whatasame.notisy.tistory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TistoryBlog {

    // https://tistory.github.io/document-tistory-apis/apis/v1/blog/list.html

    private String name;
    private String url;
    private String secondaryUrl;
    private String nickname;
    private String title;
    private String description;
    private String defaultBlog;
    private String blogIconUrl;
    private String faviconUrl;
    private String profileThumbnailImageUrl;
    private String profileImageUrl;
    private String role;
    private String blogId;
    private Statistics statistics;

    @Getter
    @AllArgsConstructor
    public static class Statistics {
        private String post;
        private String comment;
        private String trackback;
        private String guestbook;
        private String invitation;

    }
}
