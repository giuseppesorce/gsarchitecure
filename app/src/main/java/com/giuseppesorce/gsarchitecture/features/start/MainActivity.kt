package com.giuseppesorce.gsarchitecture.features.start


import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import com.giuseppesorce.architecture.views.BaseFlowActivityViewBinding
import com.giuseppesorce.gsarchitecture.databinding.ActivityMainBinding
import com.giuseppesorce.gsarchitecture.models.events.MainEvents
import com.giuseppesorce.gsarchitecture.models.events.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class MainActivity : BaseFlowActivityViewBinding<MainState, MainEvents>() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var uiStateJob: Job? = null
    override fun provideBaseViewModel(): BaseFlowViewModel<MainState, MainEvents> = viewModel

    override fun handleState(state: MainState) {

    }

    override fun handleEvent(event: MainEvents) {

    }

    override fun setupUI() {

        Handler(Looper.myLooper()!!).postDelayed({
            viewModel.onChangeState()

        }, 4000)
    }

    override fun getDataBindingiView(): View {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun handleUiState(state: MainState?) {

        when (state) {
            is MainState.Print -> {
                Log.i("asarch", " PRINT")
            }
        }

    }


}