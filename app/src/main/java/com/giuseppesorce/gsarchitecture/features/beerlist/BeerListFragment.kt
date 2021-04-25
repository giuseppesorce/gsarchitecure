package com.giuseppesorce.gsarchitecture.features.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import com.giuseppesorce.architecture.views.BaseViewBindingFragment
import com.giuseppesorce.commons.show
import com.giuseppesorce.gsarchitecture.adapters.BeersListAdapter
import com.giuseppesorce.gsarchitecture.databinding.FragmentBeerlistBinding
import com.giuseppesorce.gsarchitecture.models.Beer
import com.giuseppesorce.gsarchitecture.models.events.BeerListState
import com.giuseppesorce.gsarchitecture.models.events.BeerListEvents
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BeerListFragment : BaseViewBindingFragment<BeerListState, BeerListEvents>() {
    private val viewModel: BeerListViewModel by viewModels()
    private var _binding: FragmentBeerlistBinding? = null
    private var jobList: Job? = null

    private val binding get() = _binding!!
    override fun provideBaseViewModel(): BaseFlowViewModel<BeerListState, BeerListEvents> =
        viewModel

    private var adapter: BeersListAdapter? = BeersListAdapter(emptyList())

    override fun setupUI() {

        binding.rvList.layoutManager = LinearLayoutManager(
            activity?.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        binding.rvList.adapter = adapter

        adapter?.onActionClickListener = { item: Beer, position: Int ->

        }
    }

    override fun initFragment() {
        viewModel.loadBeersList()
    }

    override fun handleEvent(uiEvent: BeerListEvents?) {

    }

    override fun handleUiState(state: BeerListState?) {
        when (state) {
            is BeerListState.ShowOneBer -> {

            }
        }
    }

    override fun observerData() {
        jobList= lifecycleScope.launchWhenStarted {
            viewModel.beerList.collect{
                adapter?.itemsList= it
            }
        }
    }

    override fun showLoadingState() {
        binding.lottiLoad.playAnimation()
        binding.lottiLoad.show(true)
    }

    override fun hideLoadingState() {
        binding.lottiLoad.show(false)
    }

    override fun cleanFragment() {
        adapter= null
        jobList?.cancel()
        jobList= null
    }

    override fun setFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentBeerlistBinding.inflate(inflater, container, false)
        viewFrag = binding.root
    }
}

