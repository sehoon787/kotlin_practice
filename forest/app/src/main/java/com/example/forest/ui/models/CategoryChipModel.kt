package com.example.forest.ui.models

import com.example.forest.data.models.Category

/**
 * Used for Recycler View and Adapter */
data class CategoryChipModel(
    val name: String,
//    val category: Category,
    var isChecked: Boolean,
)