package com.github.whatasame.notisy.notion.repository

import com.github.whatasame.notisy.notion.NotionClientManager
import notion.api.v1.model.databases.QueryResults

class PageRepository {

    fun queryDatabase(databaseId: String): QueryResults {
        val client = NotionClientManager.getClient()

        return client.queryDatabase(
            databaseId = databaseId
        )
    }

}