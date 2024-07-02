package com.example.forest.ui.models

import com.example.forest.data.models.Category

/**
 * Used for Recycler View and Adapter */
data class CategoryFilterModel(
    val searchQuery: String? = null,
    val sortType: SortType = SortType.NONE,
    val categories: Set<Category> = setOf(),
)

enum class SortType {
    NONE,
    ALPHABETICAL_ASC,
    POPULATION_ASC
}