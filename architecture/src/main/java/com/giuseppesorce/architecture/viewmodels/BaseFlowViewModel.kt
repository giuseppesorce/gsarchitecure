@file:Suppress("PropertyName")

package com.giuseppesorce.architecture.viewmodels


import androidx.lifecycle.ViewModel
import com.giuseppesorce.architecture.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseFlowViewModel<State : Any, Event : Any> : ViewModel() {

    // UI State
   protected val _uiState = MutableStateFlow<State?>(null)
    val uiState: StateFlow<State?> = _uiState

    // UI Events
    protected val _uiEvent = MutableStateFlow<Event?>(null)
    val uiEvent: StateFlow<Event?> = _uiEvent

    // Loader
    protected val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Empty())
    val loadingState: StateFlow<LoadingState> = _loadingState

     var viewState: State?
        get() = uiState.value
        set(value) {
            _uiState.value = value
        }

    var showLoadingState: LoadingState
        get() = loadingState.value
        set(value) {
            _loadingState.value = value
        }



    protected fun showAlert(titleRes:Int, resMessage:Int) {
//        stateSimpleAlert.value= SimpleAlertData(
//            titleRes = titleRes,
//            messageRes = resMessage
//        )
    }
    protected fun showErrorAlert(title:String, message:String) {
        showAlert(title, message)

    }
    protected fun showAlert(title:String, message:String, codeMessage:Int=0) {
//        stateSimpleAlert.value=
//            SimpleAlertData(title, message, codeMessage=codeMessage)
    }

    protected fun showAlert(title:String, message:String, codeMessage:Int=0, style:Int) {
//        stateSimpleAlert.value=
//            SimpleAlertData(title, message, codeMessage=codeMessage)
    }

    protected fun emitEvent(event: Event) {
        _uiEvent.value = event
    }


    protected fun showLoading() {
        showLoadingState= LoadingState.Loading()
    }

    protected fun hideLoading() {
        showLoadingState= LoadingState.Idle()
    }

    protected fun showError(message: String = "") {
    }

    protected fun showUnknownError() {
    }

    fun dismissError() {
    }

    fun clearCommonState() {
    }
}
