package com.app.jcs.api.apimodels

import com.squareup.moshi.Json

class AdmissionFee {

    @field:Json(name = "status_code")
    var statusCode: Int? = null
    @field:Json(name = "status_text")
    var statusText: String? = null
    @field:Json(name = "id")
    var id: String? = null
    @field:Json(name = "student_id")
    var studentId: String? = null
    @field:Json(name = "fee_paid")
    var feePaid: Any? = null
    @field:Json(name = "out_of")
    var outOf: Any? = null
    @field:Json(name = "paid_date")
    var paidDate: String? = null
}