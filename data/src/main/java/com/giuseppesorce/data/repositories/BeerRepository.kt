package com.giuseppesorce.data.repositories

import com.giuseppesorce.data.api.ApiService
import com.giuseppesorce.data.responses.Beer
import com.giuseppesorce.data.responses.ErrorResponse
import com.giuseppesorce.data.network.ApiResult
import javax.inject.Inject

class BeersRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getBeers(): ApiResult<List<Beer>, ErrorResponse> {
        return apiService.beers()
    }

}

