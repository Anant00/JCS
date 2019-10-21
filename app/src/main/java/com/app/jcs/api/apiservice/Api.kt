package com.app.jcs.api.apiservice

import com.app.jcs.api.apimodels.AdmissionFee
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("admissionFee.php")
    fun getAdmissionFee(@Query("student_id") studentId: String): Flowable<List<AdmissionFee>>
}