package com.app.jcs.api.apimodels

import com.squareup.moshi.Json

class ParentLogin {

    @field:Json(name = "error")
    var error: Boolean? = null
    @field:Json(name = "father_name")
    var fatherName: String? = null
    @field:Json(name = "username")
    var username: String? = null
    @field:Json(name = "id")
    var id: Int? = null
    @field:Json(name = "mother_name")
    var motherName: String? = null
    @field:Json(name = "message")
    var message: String? = null
}
