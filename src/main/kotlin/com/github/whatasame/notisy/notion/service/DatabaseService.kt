package com.github.whatasame.notisy.notion.service

import com.github.whatasame.notisy.notion.repository.DatabaseRepository
import notion.api.v1.model.search.DatabaseSearchResult

class DatabaseService(private val databaseRepository: DatabaseRepository) {

    fun searchDatabase(title: String): DatabaseSearchResult {
        return databaseRepository.findByTitle(title)
    }

    fun getTitle(databaseSearchResult: DatabaseSearchResult): String? {
        return databaseSearchResult.title?.get(0)?.text?.content
    }

    fun getDescription(databaseSearchResult: DatabaseSearchResult): String? {
        return databaseSearchResult.description?.get(0)?.text?.content
    }


}