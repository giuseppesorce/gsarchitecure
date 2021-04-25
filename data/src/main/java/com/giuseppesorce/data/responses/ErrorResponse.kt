package com.giuseppesorce.data.responses

import kotlinx.serialization.Serializable

@Serializable
class ErrorResponse {
    var error:String?=null
    var message:String?=null

}