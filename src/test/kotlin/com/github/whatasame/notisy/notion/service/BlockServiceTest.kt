package com.github.whatasame.notisy.notion.service

import com.github.whatasame.notisy.notion.repository.BlockRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlockServiceTest {

    private lateinit var blockService: BlockService

    @BeforeEach
    fun beforeEach() {
        blockService = BlockService(BlockRepository())
    }

    @Test
    @DisplayName("[모든 블럭 가져오기] 특정 페이지의 모든 블럭을 가져온다.")
    fun test_01() {
        /* given */
        val pageId = "da36c270d65f4092a71e57762f6f35ac" // Your page id

        /* when */
        val blocks = blockService.getBlocks(pageId)

        /* then */
        assertNotEquals(0, blocks.results.size)
    }

    @Test
    @DisplayName("[HTML 변환] 블럭을 문자열로 만든다.")
    fun test_02() {
        /* given */
        val pageId = "da36c270d65f4092a71e57762f6f35ac" // Your page id

        /* when */
        val blocks = blockService.getBlocks(pageId)

        val html = blockService.getHtml(blocks.results[0])

        /* then */
        assertNotEquals("", html)
    }

}