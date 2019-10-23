package com.app.jcs.api.apimodels

import com.squareup.moshi.Json

class StudentDetail {

    @field:Json(name = "id")
    var id: Int? = null
    @field:Json(name = "name")
    var name: String? = null
}