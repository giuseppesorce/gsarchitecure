package com.giuseppesorce.gsarchitecture.models.events

import com.giuseppesorce.architecture.model.StateUi

sealed class EmptyState
sealed class EmptyEvents

 sealed  class MainState {
     object Print:MainState()
}

sealed class MainEvents {

}

data class ArticleHeadline(var value:String)


// Represents different states for the LatestNews screen
sealed class LatestNewsUiState {
    data class Success(var news: List<ArticleHeadline>): LatestNewsUiState()
    data class Error(var exception: Throwable): LatestNewsUiState()
}