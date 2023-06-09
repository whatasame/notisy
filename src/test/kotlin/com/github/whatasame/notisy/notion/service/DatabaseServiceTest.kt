package com.github.whatasame.notisy.notion.service

import com.github.whatasame.notisy.notion.repository.DatabaseRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DatabaseRepositoryTest {


    private lateinit var databaseService: DatabaseService

    @BeforeEach
    fun beforeEach() {
        databaseService = DatabaseService(DatabaseRepository())
    }

    @Test
    @DisplayName("검색 제목과 일치하는 데이터베이스 가져오기")
    fun getDatabaseByTitle() {
        /* given */
        val title = "Notisy Test"

        /* then */
        assertDoesNotThrow {
            databaseService.searchDatabase(title) // when
        }
    }


}