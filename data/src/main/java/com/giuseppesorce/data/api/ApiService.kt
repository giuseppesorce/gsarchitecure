package com.giuseppesorce.data.api

import com.giuseppesorce.data.responses.Beer
import com.giuseppesorce.data.network.ApiResult
import com.giuseppesorce.data.network.DecodeErrorBody
import com.giuseppesorce.data.responses.ErrorResponse
import retrofit2.http.GET

/**
 * @author Giuseppe Sorce
 */
interface ApiService {
    @DecodeErrorBody
    @GET("v2//beerss")
    suspend fun beers() : ApiResult<List<Beer>, ErrorResponse>

}