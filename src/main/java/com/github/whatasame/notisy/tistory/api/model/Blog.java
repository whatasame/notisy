package com.github.whatasame.notisy.tistory.api.model;

import com.google.gson.annotations.SerializedName;

public record Blog(
        String name,
        String url,
        String secondaryUrl,
        String nickname,
        String title,
        String description,
        @SerializedName("default")
        String isDefault,
        String blogIconUrl,
        String faviconUrl,
        String profileThumbnailImageUrl,
        String profileImageUrl,
        String role,
        String blogId,
        String isEmpty,
        Statistics statistics
) {
}

