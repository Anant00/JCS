package com.app.jcs.view.activities.main

import com.app.jcs.api.apimodels.AdmissionFee
import com.app.jcs.api.apimodels.Fees
import com.app.jcs.api.apimodels.StudentDetail
import com.app.jcs.api.apimodels.TransportFees
import com.app.jcs.api.apiservice.Api
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MainRepo(private val api: Api) {

    fun getStudentDetailByParentId(parentId: String): Observable<List<StudentDetail>> {
        return api.getStudentDetail(parentId)
            .toObservable()
            .subscribeOn(Schedulers.io())
    }

    fun getAdmissionFeeByStudentId(studentId: String): Observable<List<AdmissionFee>> {
        return api.getAdmissionFee(studentId)
            .toObservable()
            .subscribeOn(Schedulers.io())
    }

    fun getFeesByClassId(classId: String): Observable<List<Fees>> {
        return api.getFees(classId)
            .toObservable()
            .subscribeOn(Schedulers.io())
    }

    fun getAnnualFeeByStudentId(studentId: String): Observable<List<AdmissionFee>> {
        return api.getAnnualFee(studentId)
            .toObservable()
            .subscribeOn(Schedulers.io())
    }

    fun getTransportFeeByStudentId(studentId: String): Observable<List<TransportFees>> {
        return api.getTransportFee(studentId)
            .toObservable()
            .subscribeOn(Schedulers.io())
    }
}
