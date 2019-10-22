package com.app.jcs.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.jcs.api.apimodels.AdmissionFee
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
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Log.d(tag, "parent name: ${it.motherName}")
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

    private fun getDataOnNext(list: List<AdmissionFee>) {
        Log.d(tag, "onNext: ${list[0].studentId}")
    }
}