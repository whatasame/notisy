package com.github.whatasame.notisy.notion.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DatabaseRepositoryTest {

    private lateinit var databaseRepository: DatabaseRepository

    @BeforeEach
    fun beforeEach() {
        databaseRepository = DatabaseRepository()
    }

    @Test
    @DisplayName("검색 제목과 일치하는 데이터베이스 가져오기")
    fun getDatabaseByTitle() {
        /* given */
        val title = "Notisy Test"

        /* then */
        assertDoesNotThrow {
            databaseRepository.findByTitle(title) // when
        }
    }


}