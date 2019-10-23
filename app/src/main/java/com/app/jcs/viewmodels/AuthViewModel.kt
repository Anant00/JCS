package com.app.jcs.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.jcs.api.apiservice.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AuthViewModel(
    private val api: Api,
    private val disposable: CompositeDisposable
) : ViewModel() {

    private val tag by lazy {
        javaClass.simpleName
    }

    fun loginUser() {
        disposable.add(
            api.login("JCS-PR-0001", "password")
                .subscribeOn(Schedulers.io())
                .flatMap { result ->
                    api.getStudentDetail(result.id.toString())
                }
                .flatMap {
                    api.getAdmissionFee(it[0].id.toString())
                }
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Log.d(tag, "parent name: ${it[0].studentId}")
                    },
                    onError = {
                        getDataOnError(it)
                    },
                    onComplete = {
                        getDataOnComplete()
                    }
                )
        )
    }

    private fun getDataOnComplete() {
        Log.d(tag, "onComplete")
    }

    private fun getDataOnError(it: Throwable) {
        Log.d(tag, "onDataError: ${it.localizedMessage}")
    }
}