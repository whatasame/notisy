package com.github.whatasame.notisy.notion.service

import com.github.whatasame.notisy.notion.property.NotisyProperty.POST_ID
import com.github.whatasame.notisy.notion.repository.PageRepository
import notion.api.v1.model.pages.Page

class PageService(
    private val pageRepository: PageRepository
) {

    fun getPages(databaseId: String): List<Page> {

        return pageRepository
            .queryDatabase(databaseId)
            .results
    }

    fun updatePostId(page: Page, writtenPostId: String): Page {
        return pageRepository.updateProperty(page, POST_ID, writtenPostId)
    }

}