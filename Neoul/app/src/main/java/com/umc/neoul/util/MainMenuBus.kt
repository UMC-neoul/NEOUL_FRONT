package com.umc.neoul.util

import com.umc.neoul.presentation.main.MainMenuId
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainMenuBus {
    private val _mainMenuFlow = MutableSharedFlow<MainMenuId>()
    val mainTabFlow = _mainMenuFlow.asSharedFlow()

    suspend fun changMenu(id : MainMenuId){
        _mainMenuFlow.emit(id)
    }
}