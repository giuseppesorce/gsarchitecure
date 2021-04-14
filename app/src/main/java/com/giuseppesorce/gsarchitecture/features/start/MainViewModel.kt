package com.giuseppesorce.gsarchitecture.features.start

import androidx.hilt.lifecycle.ViewModelInject
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel

import com.giuseppesorce.gsarchitecture.models.events.MainEvents
import com.giuseppesorce.gsarchitecture.models.events.MainState

/**
 * @author Giuseppe Sorce
 */
class MainViewModel @ViewModelInject constructor(

) :
    BaseFlowViewModel<MainState, MainEvents>() {
    fun onChangeState() {

        _uiState.value = MainState.Print

    }


}