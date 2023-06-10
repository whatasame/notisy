package com.github.whatasame.notisy.notion.repository

import com.github.whatasame.notisy.notion.NotionClientManager
import com.github.whatasame.notisy.notion.property.NotisyProperty
import notion.api.v1.model.databases.QueryResults
import notion.api.v1.model.pages.Page
import notion.api.v1.model.pages.PageProperty

class PageRepository {

    fun queryDatabase(databaseId: String): QueryResults {
        val client = NotionClientManager.getClient()

        return client.queryDatabase(
            databaseId = databaseId
        )
    }

    fun updateProperty(page: Page, postId: NotisyProperty, writtenPostId: String): Page {
        val client = NotionClientManager.getClient()

        val propertyId = page.properties[postId.value]!!.id

        return client.updatePage(
            pageId = page.id,
            properties = mapOf(
                propertyId to PageProperty(number = writtenPostId.toInt()),
            )
        )
    }
}