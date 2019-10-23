package com.app.jcs.api.apimodels

import com.squareup.moshi.Json

class StudentDetail {

    @field:Json(name = "id")
    val id: Int? = null
    @field:Json(name = "name")
    val name: String? = null
}