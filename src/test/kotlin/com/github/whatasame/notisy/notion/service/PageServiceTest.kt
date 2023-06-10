package com.github.whatasame.notisy.notion.service

import com.github.whatasame.notisy.notion.repository.PageRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PageServiceTest {

    private lateinit var pageService: PageService

    @BeforeEach
    fun beforeEach() {
        pageService = PageService(PageRepository())
    }

    @Test
    @DisplayName("[모든 페이지 가져오기] 특정 데이터베이스의 모든 페이지를 가져온다.")
    fun test_01() {
        /* given */
        val databaseId = "298c4de237104e5dae6f89052806bf11" // Your database id

        /* when */
        val pages = pageService.getPages(databaseId)

        /* then */
        assertNotEquals(0, pages.size)

    }

}