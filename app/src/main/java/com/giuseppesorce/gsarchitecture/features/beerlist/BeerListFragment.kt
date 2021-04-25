package com.giuseppesorce.gsarchitecture.features.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import com.giuseppesorce.architecture.views.BaseViewBindingFragment
import com.giuseppesorce.gsarchitecture.databinding.FragmentBeerlistBinding
import com.giuseppesorce.gsarchitecture.models.events.BeerListState
import com.giuseppesorce.gsarchitecture.models.events.BeerListEvents
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BeerListFragment : BaseViewBindingFragment<BeerListState, BeerListEvents>() {
    private val viewModel: BeerListViewModel by viewModels()
    private var _binding: FragmentBeerlistBinding? = null
    private val binding get() = _binding!!
    override fun provideBaseViewModel(): BaseFlowViewModel<BeerListState, BeerListEvents> =
        viewModel

    override fun setupUI() {
    }

    override fun initFragment() {
        viewModel.loadBeersList()
    }

    override fun handleEvent(uiEvent: BeerListEvents?) {

    }

    override fun handleUiState(state: BeerListState?) {
        when (state) {
            is BeerListState.ShowOneBer -> {
                Toast.makeText(context, "Beer ${state.name}", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun setFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentBeerlistBinding.inflate(inflater, container, false)
        viewFrag = binding.root
    }
}

