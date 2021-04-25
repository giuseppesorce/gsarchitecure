package com.giuseppesorce.gsarchitecture.features.beerlist

import androidx.lifecycle.viewModelScope
import com.giuseppesorce.architecture.viewmodels.BaseFlowViewModel
import com.giuseppesorce.gsarchitecture.domain.interactors.GetBeersListUseCase
import com.giuseppesorce.gsarchitecture.models.events.BeerListEvents
import com.giuseppesorce.gsarchitecture.models.events.BeerListState
import com.giuseppesorce.data.network.ApiResult
import com.giuseppesorce.data.network.ApiResult.Failure
import com.giuseppesorce.data.responses.SBeer
import com.giuseppesorce.data.responses.ErrorResponse
import com.giuseppesorce.gsarchitecture.mappers.UIMapper
import com.giuseppesorce.gsarchitecture.models.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


/**
 * @author Giuseppe Sorce
 */
@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val uIMapper: UIMapper,
    private val getBeersListUseCase: GetBeersListUseCase
) :
    BaseFlowViewModel<BeerListState, BeerListEvents>() {

    private val _beerList = MutableStateFlow(emptyList<Beer>())
    val beerList: StateFlow<List<Beer>> = _beerList




    fun loadBeersList() {
        showLoading()
        viewModelScope.launch {
            val result = getBeersListUseCase.invoke()
            hideLoading()
            when (result) {
                is ApiResult.Success -> showBeers(result.response)
                is Failure -> when (result) {
                    is Failure.NetworkFailure -> checkError(result.error)
                    is Failure.HttpFailure -> checkErrorHttp(result)
                    is Failure.ApiFailure -> checkErrorApi(result.error)
                    is Failure.UnknownFailure -> checkErrorUnKnow(result.error)
                }
            }
        }
    }

    private fun checkErrorUnKnow(error: Throwable) {
        Timber.tag("beer").d("checkErrorUnKnow: ${error.localizedMessage}")
    }

    private fun checkErrorApi(error: ErrorResponse?) {
        Timber.tag("beer").d("checkErrorApi: ${error}")
    }

    private fun checkErrorHttp(result: Failure<ErrorResponse>) {

        var error = (result as? Failure.HttpFailure)?.error

        Timber.tag("beer").d("checkErrorHttp: ${error?.message}")
    }


    private fun checkError(error: IOException) {
        Timber.tag("beer").d("IOException: ${error.localizedMessage}")
    }

    private fun showBeers(response: List<SBeer>) {
        _beerList.value= uIMapper.getBeers(response)

    }
}