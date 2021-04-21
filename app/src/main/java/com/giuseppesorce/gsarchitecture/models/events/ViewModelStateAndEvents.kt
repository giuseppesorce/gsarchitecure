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


sealed class BeerListState{

}

sealed class BeerListEvents{

}