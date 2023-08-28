package com.umc.neoul.util

import com.umc.neoul.presentation.main.category.CategoryMenu
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class CategoryMenuBus {
    private val _categoryMenuFlow = MutableSharedFlow<CategoryMenu>()
    val category = _categoryMenuFlow.asSharedFlow()

    suspend fun changeMenu( position : CategoryMenu){
        _categoryMenuFlow.emit(position)
    }
}