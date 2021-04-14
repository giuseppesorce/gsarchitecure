package com.giuseppesorce.architecture.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.giuseppesorce.architecture.LoadingState
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFlowActivityViewBinding<State : Any, Event : Any>() : AppCompatActivity() {


    abstract fun provideBaseViewModel(): BaseFlowViewModel<State, Event>
    abstract fun handleState(state: State)

    private var uiStateJob: Job? = null
    private var uiEventJob: Job? = null
    private var uiLoadingob: Job? = null
    abstract fun setupUI()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getDataBindingiView())
        setupUI()
        observerData()
        initActivity()
        if (intent != null && intent.extras != null) {
            onGetIntent(intent)
        }
        uiStateJob = lifecycleScope.launch {
           provideBaseViewModel().uiState.collect { uiState -> handleUiState(uiState) }
        }
        uiEventJob = lifecycleScope.launch {

            provideBaseViewModel().uiEvent.collect { uiEvent -> handleEvent(uiEvent) }
        }
        uiLoadingob=lifecycleScope.launch {
            provideBaseViewModel().loadingState.collect { loading ->
                handleCommonState(loading)
            }
        }
    }

    abstract fun handleEvent(uiEvent: Event?)

    abstract fun handleUiState(state: State?)

    open fun onGetIntent(intent: Intent?) {
    }

    abstract fun getDataBindingiView(): View
    open fun initActivity() {
    }

    override fun onDestroy() {
        super.onDestroy()
        uiEventJob?.cancel()
        uiStateJob?.cancel()
        uiLoadingob?.cancel()
    }

    open fun observerData() {
    }

    private fun handleCommonState(commonState: LoadingState) {
        handleLoading(commonState)
    }

    open fun handleLoading(loadingState: LoadingState?) {
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
}