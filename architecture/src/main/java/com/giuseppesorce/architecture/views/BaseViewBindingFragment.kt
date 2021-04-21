package com.giuseppesorce.architecture.views
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.giuseppesorce.architecture.LoadingState
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * @author Giuseppe Sorce
 */

abstract class BaseViewBindingFragment<State : Any, Event : Any>() : Fragment() {



    open var viewFrag: View? = null
    private var uiStateJob: Job? = null
    private var uiEventJob: Job? = null
    private var uiLoadingob: Job? = null
    private var fragmentInitialized = false
    abstract fun provideBaseViewModel(): BaseFlowViewModel<State, Event>
    abstract fun setupUI()
    abstract fun handleEvent(uiEvent: Event?)

    abstract fun handleUiState(state: State?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewFrag == null) {
            setFragmentViewBinding(inflater, container)
        }
        return viewFrag
    }

    abstract fun setFragmentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        intitializeFragment()
    }

     override fun onSaveInstanceState(oldInstanceState: Bundle) {
        super.onSaveInstanceState(oldInstanceState)
        oldInstanceState.clear()
    }



    fun intitializeFragment() {
        setupUI()
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
//        if (!fragmentInitialized) {
//            fragmentInitialized = true
            observerData()
            initFragment()
     //   }
    }

    override fun onDestroy() {
        viewFrag = null
        super.onDestroy()
    }

    override fun onDestroyView() {
        viewFrag = null
        cleanFragment()
        super.onDestroyView()
    }

    open fun cleanFragment() {
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
    open fun initFragment() {
    }

    open fun observerData() {
    }

}