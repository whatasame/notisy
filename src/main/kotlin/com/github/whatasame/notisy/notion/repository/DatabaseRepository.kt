package com.github.whatasame.notisy.notion.repository

import com.github.whatasame.notisy.notion.NotionClientManager
import com.github.whatasame.notisy.notion.property.NotisyProperty
import notion.api.v1.model.search.DatabaseSearchResult
import notion.api.v1.model.search.SearchResult
import notion.api.v1.request.search.SearchRequest


class DatabaseRepository {

    fun findByTitle(title: String): DatabaseSearchResult? {
        val client = NotionClientManager.getClient()

        return client
                .search(
                        query = title,
                        filter = SearchRequest.SearchFilter(value = "database", property = "object")
                )
                .results
                .find {
                    validateProperties(it)
                }?.asDatabase()
    }

    private fun validateProperties(searchResult: SearchResult): Boolean {
        val properties = searchResult.asDatabase().properties

        for (notisyProperty in NotisyProperty.values()) {
            if (!properties.containsKey("[Notisy] " + notisyProperty.value)) {
                return false
            }
        }

        return true
    }
}