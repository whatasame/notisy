package com.github.whatasame.notisy.notion.service

import com.github.whatasame.notisy.notion.repository.DatabaseRepository
import notion.api.v1.model.search.DatabaseSearchResult

class DatabaseService(private val databaseRepository: DatabaseRepository) {

    fun searchDatabase(title: String): DatabaseSearchResult {
        return databaseRepository.findByTitle(title)
                ?: error("Notisy database를 찾을 수 없습니다. API 연결 혹은 데이터 베이스의 이름과 속성을 확인해주세요. ")
    }

    fun getTitle(databaseSearchResult: DatabaseSearchResult): String? {
        return databaseSearchResult.title?.get(0)?.text?.content
    }

    fun getDescription(databaseSearchResult: DatabaseSearchResult): String? {
        return databaseSearchResult.description?.get(0)?.text?.content
    }


}