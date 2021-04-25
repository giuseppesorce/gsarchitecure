package com.giuseppesorce.gsarchitecture.features.start

import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel

import com.giuseppesorce.gsarchitecture.models.events.MainEvents
import com.giuseppesorce.gsarchitecture.models.events.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
@HiltViewModel
class MainViewModel @Inject constructor(

) :
    BaseFlowViewModel<MainState, MainEvents>() {
    fun onChangeState() {

        showLoading()
        _uiState.value = MainState.Print

    }


}