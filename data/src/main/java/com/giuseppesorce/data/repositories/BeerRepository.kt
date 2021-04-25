package com.giuseppesorce.data.repositories

import com.giuseppesorce.data.api.ApiService
import com.giuseppesorce.data.responses.SBeer
import com.giuseppesorce.data.responses.ErrorResponse
import com.giuseppesorce.data.network.ApiResult
import javax.inject.Inject

class BeersRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getBeers(): ApiResult<List<SBeer>, ErrorResponse> {
        return apiService.beers()
    }

}

