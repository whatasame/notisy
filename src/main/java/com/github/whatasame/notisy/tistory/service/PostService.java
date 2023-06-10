package com.github.whatasame.notisy.tistory.service;

import com.github.whatasame.notisy.key.KeyManager;
import com.github.whatasame.notisy.notion.service.BlockService;
import com.github.whatasame.notisy.notion.service.PageService;
import com.github.whatasame.notisy.tistory.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import notion.api.v1.model.blocks.Block;
import notion.api.v1.model.blocks.Blocks;
import notion.api.v1.model.pages.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.whatasame.notisy.key.Key.CATEGORY_NAME;
import static com.github.whatasame.notisy.notion.property.NotisyProperty.POST_ID;
import static com.github.whatasame.notisy.notion.property.NotisyProperty.TITLE;

@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BlockService blockService;
    private final CategoryService categoryService;
    private final KeyManager keyManager;
    private final PageService pageService;

    public void syncPost(String blogName, List<Page> pages) {
        for (Page page : pages) {
            Number postId = getPostId(page);

            if (Objects.isNull(postId)) {
                String writtenPostId = postRepository.writePost(
                        blogName,
                        getTitle(page),
                        generateContent(page),
                        categoryService.getCategoryId(
                                blogName,
                                keyManager.readKey(CATEGORY_NAME)
                        )
                );

                pageService.updatePostId(page, writtenPostId);
            } else {
                postRepository.modifyPost(
                        blogName,
                        getTitle(page),
                        generateContent(page),
                        categoryService.getCategoryId(
                                blogName,
                                keyManager.readKey(CATEGORY_NAME)
                        ),
                        postId.toString()
                );
            }
        }
    }

    private String getTitle(Page page) {
        return page.getProperties().get(TITLE.getValue()).getTitle().get(0).getPlainText();
    }

    private Number getPostId(Page page) {
        return page.getProperties().get(POST_ID.getValue()).getNumber();
    }

    private String generateContent(Page page) {
        String pageId = page.getId();
        Blocks blocks = blockService.getBlocks(pageId);
        List<Block> blockList = blocks.getResults();
        return blockList.stream()
                .map(blockService::getHtml)
                .collect(Collectors.joining());
    }
}
