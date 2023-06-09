package com.github.whatasame.notisy.notion

import com.github.whatasame.notisy.key.Key.NOTION_TOKEN
import com.github.whatasame.notisy.key.KeyManager
import notion.api.v1.NotionClient

object NotionClientManager {

    fun getClient(): NotionClient {
        val token: String = KeyManager().readKey(NOTION_TOKEN)

        return NotionClient(token)
    }

}