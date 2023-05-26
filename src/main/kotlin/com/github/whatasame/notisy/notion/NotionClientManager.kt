package com.github.whatasame.notisy.notion

import com.github.whatasame.notisy.key.Key.NOTION_TOKEN
import com.github.whatasame.notisy.key.KeyManager
import notion.api.v1.NotionClient

object NotionClientManager {

    private var client: NotionClient? = null

    fun getClient(): NotionClient {
        if (client == null) {
            client = refreshClient()
        }

        return client!!
    }

    fun refreshClient(): NotionClient {
        val token: String = KeyManager().readKey(NOTION_TOKEN)

        return NotionClient(token)
    }

}