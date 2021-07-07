package com.example.imagelist.ui.main.view

sealed class MainStateEvent {
    data class GetImageState(val count: Int) : MainStateEvent()

}
