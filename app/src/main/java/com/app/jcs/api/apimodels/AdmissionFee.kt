package com.app.jcs.api.apimodels

import com.squareup.moshi.Json

class AdmissionFee {

    @Json(name = "status_code")
    var statusCode: Int? = null
    @Json(name = "status_text")
    var statusText: String? = null
    @Json(name = "id")
    var id: String? = null
    @Json(name = "student_id")
    var studentId: String? = null
    @Json(name = "fee_paid")
    var feePaid: Any? = null
    @Json(name = "out_of")
    var outOf: Any? = null
    @Json(name = "paid_date")
    var paidDate: String? = null
}