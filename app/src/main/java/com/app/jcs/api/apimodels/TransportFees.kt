package com.app.jcs.api.apimodels

import com.squareup.moshi.Json

class TransportFees {

    @field:Json(name = "user_id")
    val userId: String? = null
    @field:Json(name = "paid_upto_date")
    var paidUpToDate: String? = null
    @field:Json(name = "destination_id")
    var destinationId: String? = null
    @field:Json(name = "amount")
    var amount: String? = null
}
