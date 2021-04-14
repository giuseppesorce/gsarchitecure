package com.giuseppesorce.architecture.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.giuseppesorce.architecture.model.StateUi
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import com.giuseppesorce.architecture.viewmodels.BaseViewModel

import it.milkman.architecture.CommonState
import it.milkman.architecture.ErrorState
import it.milkman.architecture.LoadingState
import kotlinx.coroutines.flow.collect
abstract class BaseFlowActivityViewBinding<State : Any, Event : Any>() : AppCompatActivity() {


    abstract fun provideBaseViewModel(): BaseFlowViewModel<State ,  Event>
    abstract fun handleState(state: State)

    abstract fun handleEvent(event: Event)
    abstract fun setupUI()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getDataBindingiView())
        provideBaseViewModel()?.stateHolder()
            .observe(this, Observer { state -> handleState(state) })
        provideBaseViewModel()?.commonStateHolder()
            .observe(this, Observer { state -> handleCommonState(state) })
        provideBaseViewModel()?.eventHolder()
            .observe(this, Observer { event -> handleEvent(event) })
        setupUI()
        observerData()
        initActivity()
        if(intent !=null && intent.extras !=null){
            onGetIntent(intent)
        }

        lifecycleScope.launchWhenStarted {
            // Triggers the flow and starts listening for values
            provideBaseViewModel().uiState.collect { uiState ->
               handleUiState(uiState)

            }
        }


    }

    abstract fun handleUiState(state: State?)

    open fun onGetIntent(intent: Intent?) {
    }

    abstract fun getDataBindingiView(): View
    open fun initActivity() {
    }

    open fun observerData() {
    }

    private fun handleCommonState(commonState: CommonState) {
        handleLoading(commonState.loadingState)
        handleError(commonState.errorState)
    }

    open fun handleLoading(loadingState: LoadingState) {
        when (loadingState) {
            is LoadingState.Loading -> showLoadingState()
            is LoadingState.Idle -> showIdleState()
        }
    }

    open fun showLoadingState() {
    }

    open fun hideLoadingState() {
    }

    open fun showIdleState() {
        hideLoadingState()
    }

    open fun handleError(errorState: ErrorState) {
        when (errorState) {
            is ErrorState.UnknownError -> displayUnknownError()
            is ErrorState.Error -> displayUnknownError()
        }
    }

    open fun displayUnknownError() {
    }


}