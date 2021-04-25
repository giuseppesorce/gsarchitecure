package com.giuseppesorce.gsarchitecture.mappers


import com.giuseppesorce.data.responses.SBeer
import com.giuseppesorce.gsarchitecture.models.Beer
import javax.inject.Inject

class UIMapper @Inject constructor() {
    fun getBeers(response: List<SBeer>): List<Beer> {

        return response.map {
            Beer(name= it.name ?: "", image = it.image_url)
        }
    }


}