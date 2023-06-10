package com.github.whatasame.notisy.notion.repository

import com.github.whatasame.notisy.notion.NotionClientManager
import notion.api.v1.model.blocks.Blocks

class BlockRepository {

    fun getBlocks(pageId: String): Blocks {
        val client = NotionClientManager.getClient()

        return client.retrieveBlockChildren(
            blockId = pageId
        )
    }

}