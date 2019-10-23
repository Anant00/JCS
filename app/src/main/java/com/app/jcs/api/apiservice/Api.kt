package com.app.jcs.api.apiservice

import com.app.jcs.api.apimodels.AdmissionFee
import com.app.jcs.api.apimodels.Fees
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.api.apimodels.StudentDetail
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @GET("includes/admissionFee.php")
    fun getAdmissionFee(
        @Query("student_id") studentId: String
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
}