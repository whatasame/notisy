package com.github.whatasame.notisy.notion.repository

import com.github.whatasame.notisy.notion.NotionClientManager
import com.github.whatasame.notisy.notion.property.NotisyProperty
import notion.api.v1.model.search.DatabaseSearchResult
import notion.api.v1.model.search.SearchResult
import notion.api.v1.request.search.SearchRequest


class NotionDatabaseRepository {

    fun findByTitle(title: String): DatabaseSearchResult {
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
            ?: error("Notisy database를 찾을 수 없습니다. API 연결 혹은 데이터 베이스의 이름과 속성을 확인해주세요. ")
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