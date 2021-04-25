package com.giuseppesorce.gsarchitecture.domain.interactors

import com.giuseppesorce.data.repositories.BeersRepository
import com.giuseppesorce.data.responses.SBeer
import com.giuseppesorce.data.responses.ErrorResponse
import com.giuseppesorce.data.network.ApiResult
import javax.inject.Inject


class GetBeersListUseCase @Inject constructor(private val beersRepository: BeersRepository) {
    suspend operator fun invoke(
    ): ApiResult<List<SBeer>, ErrorResponse> {
        return beersRepository.getBeers()
    }
}