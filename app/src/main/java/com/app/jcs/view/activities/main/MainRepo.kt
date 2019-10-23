package com.app.jcs.view.activities.main

import com.app.jcs.api.apimodels.StudentDetail
import com.app.jcs.api.apiservice.Api
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MainRepo(private val api: Api) {

    fun getStudentDetailByParentId(parentId: String): Observable<List<StudentDetail>> {
        return api.getStudentDetail(parentId)
            .toObservable()
            .subscribeOn(Schedulers.io())

    }
}