package com.app.jcs.api.apiservice

import com.app.jcs.api.apimodels.*
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("includes/admissionFee.php")
    fun getAdmissionFee(
        @Field("student_id") studentId: String
    ): Single<List<AdmissionFee>>

    @FormUrlEncoded
    @POST("v1/ParentsLogin.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<ParentLogin>

    @FormUrlEncoded
    @POST("includes/StudentViewForParent.php")
    fun getStudentDetail(
        @Field("parent_id") parentId: String
    ): Single<List<StudentDetail>>

    @FormUrlEncoded
    @POST("includes/getFees.php")
    fun getFees(
        @Field("class_id") classId: String
    ): Single<List<Fees>>

    @FormUrlEncoded
    @POST("includes/getAnnualFee.php")
    fun getAnnualFee(
        @Field("student_id") studentId: String
    ): Single<List<AdmissionFee>>

    @FormUrlEncoded
    @POST("includes/getTransportFees.php")
    fun getTransportFee(
        @Field("student_id") studentId: String
    ): Single<List<TransportFees>>
}