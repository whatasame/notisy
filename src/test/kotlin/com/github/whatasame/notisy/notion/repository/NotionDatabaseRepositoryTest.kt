package com.github.whatasame.notisy.notion.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NotionDatabaseRepositoryTest {

    private lateinit var notionDatabaseRepository: NotionDatabaseRepository

    @BeforeEach
    fun beforeEach() {
        notionDatabaseRepository = NotionDatabaseRepository()
    }

    @Test
    @DisplayName("검색 제목과 일치하는 데이터베이스 가져오기")
    fun getDatabaseByTitle() {
        /* given */
        val title = "Notisy Test"

        /* then */
        assertDoesNotThrow {
            notionDatabaseRepository.findByTitle(title) // when
        }
    }


}