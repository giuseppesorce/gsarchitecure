@file:Suppress("PropertyName")

package com.giuseppesorce.architecture.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giuseppesorce.architecture.LiveEvent
import it.milkman.architecture.*


import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel<State : Any, Event : Any> : ViewModel() {


     private val stateLiveData: MutableLiveData<State> = MutableLiveData()


    private val stateSimpleAlert: MutableLiveData<SimpleAlertData> = MutableLiveData()
    private val commonStateLiveData: MutableLiveData<CommonState> = MutableLiveData()
     val eventLiveData: LiveEvent<Event> = LiveEvent()

    fun stateHolder(): LiveData<State> = stateLiveData
    fun commonStateHolder(): LiveData<CommonState> = commonStateLiveData
    fun eventHolder(): LiveData<Event> = eventLiveData
    fun alertLiveData(): LiveData<SimpleAlertData> = stateSimpleAlert

     var viewState: State?
        get() = stateLiveData.value
        set(value) {
            stateLiveData.value = value
        }

    private var commonState: CommonState
        get() = commonStateLiveData.value ?: CommonState()
        set(value) {
            commonStateLiveData.value = value
        }



    protected fun showAlert(titleRes:Int, resMessage:Int) {
        stateSimpleAlert.value= SimpleAlertData(
            titleRes = titleRes,
            messageRes = resMessage
        )
    }
    protected fun showErrorAlert(title:String, message:String) {
        showAlert(title, message)

    }
    protected fun showAlert(title:String, message:String, codeMessage:Int=0) {
        stateSimpleAlert.value=
            SimpleAlertData(title, message, codeMessage=codeMessage)
    }

    protected fun showAlert(title:String, message:String, codeMessage:Int=0, style:Int) {
        stateSimpleAlert.value=
            SimpleAlertData(title, message, codeMessage=codeMessage)
    }

    protected fun emitEvent(event: Event) {
        eventLiveData.value = event
    }

    override fun onCleared() {
        // dispose and cancel all the current stuff
    }

    protected fun showLoading() {
        commonState = commonState.copy(loadingState = LoadingState.Loading)
    }

    protected fun hideLoading() {
        commonState = commonState.copy(loadingState = LoadingState.Idle)
    }

    protected fun showError(message: String = "") {
        commonState = commonState.copy(errorState = ErrorState.Error(message))
    }

    protected fun showUnknownError() {
        commonState = commonState.copy(errorState = ErrorState.UnknownError)
    }

    fun dismissError() {
        commonState = commonState.copy(errorState = ErrorState.NoError)
    }

    fun clearCommonState() {
        commonState = CommonState()
    }
}
