package com.github.whatasame.notisy.notion.service

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

}