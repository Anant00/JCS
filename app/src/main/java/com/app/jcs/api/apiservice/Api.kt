package com.app.jcs.api.apiservice

import com.app.jcs.api.apimodels.AdmissionFee
import com.app.jcs.api.apimodels.ParentLogin
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @GET("includes/admissionFee.php")
    fun getAdmissionFee(@Query("student_id") studentId: String): Flowable<List<AdmissionFee>>

    @FormUrlEncoded
    @POST("v1/ParentsLogin.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<ParentLogin>
}