package com.giuseppesorce.gsarchitecture.features.beerlist

import androidx.hilt.lifecycle.ViewModelInject
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import com.giuseppesorce.gsarchitecture.models.events.BeerListEvents
import com.giuseppesorce.gsarchitecture.models.events.BeerListState

/**
 * @author Giuseppe Sorce
 */
class BeerListViewModel @ViewModelInject constructor(

) :
    BaseFlowViewModel<BeerListState, BeerListEvents>() {


    fun loadBeersList() {

    }


}