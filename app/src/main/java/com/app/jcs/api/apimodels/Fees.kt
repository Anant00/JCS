package com.app.jcs.api.apimodels

import com.squareup.moshi.Json

class Fees {

    @Json(name = "id")
    var id: String? = null
    @Json(name = "amount")
    var amount: String? = null
    @Json(name = "fee_per_month")
    var feePerMonth: String? = null
    @Json(name = "admission")
    var admission: String? = null
    @Json(name = "annual")
    var annual: String? = null
}
