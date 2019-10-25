package com.app.jcs.view.activities.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.jcs.api.apimodels.AdmissionFee
import com.app.jcs.api.apimodels.Fees
import com.app.jcs.api.apimodels.StudentDetail
import com.app.jcs.api.apimodels.TransportFees
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ViewModelMain(
    private val mainRepo: MainRepo,
    private val disposable: CompositeDisposable
) : ViewModel() {
    private val tag by lazy {
        javaClass.simpleName
    }
    private var studentDetail: MutableLiveData<List<StudentDetail>>? = null
    private var admissionFee: MutableLiveData<List<AdmissionFee>>? = null
    private var feesList: MutableLiveData<List<Fees>>? = null
    private var annualFeesList: MutableLiveData<List<AdmissionFee>>? = null
    private var transportFee: MutableLiveData<List<TransportFees>>? = null



    private fun getStudentDetailByParentId(parentId: String) {
        disposable.add(
            mainRepo.getStudentDetailByParentId(parentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    studentDetail?.value = it
                }
        )
    }

    private fun getAdmissionFeeByStudentId(studentId: String) {
        disposable.add(
            mainRepo.getAdmissionFeeByStudentId(studentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    admissionFee?.value = it
                }
        )
    }

    private fun getFeesByClassId(classId: String) {
        disposable.add(
            mainRepo.getFeesByClassId(classId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    feesList?.value = it
                }
        )
    }

    private fun getAnnualFeeByStudentId(studentId: String) {
        disposable.add(
            mainRepo.getAnnualFeeByStudentId(studentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    annualFeesList?.value = it
                }, {
                    Log.d(tag, "error :${it.localizedMessage} ")
                })
        )
    }

    private fun getTransportFeeByStudentId(studentId: String) {
        disposable.add(
            mainRepo.getTransportFeeByStudentId(studentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    transportFee?.value = it
                    Log.d(tag, "returned value :${it[0].userId} ")
                }, {
                    Log.d(tag, "transport error is:${it.localizedMessage} ")
                })
        )
    }

    fun getStudentDetail(parentId: String): LiveData<List<StudentDetail>>? {
        if (studentDetail == null) {
            studentDetail = MutableLiveData()
            getStudentDetailByParentId(parentId)
        }
        return studentDetail
    }

    fun getAdmissionFee(studentId: String): LiveData<List<AdmissionFee>>? {
        if (admissionFee == null) {
            admissionFee = MutableLiveData()
            getAdmissionFeeByStudentId(studentId)
        }
        return admissionFee
    }

    fun getFee(classId: String): LiveData<List<Fees>>? {
        if (feesList == null) {
            Log.d(tag, "data null: creating again")
            feesList = MutableLiveData()
            getFeesByClassId(classId)
        }
        return feesList
    }

    fun getAnnualFee(studentId: String): LiveData<List<AdmissionFee>>? {
        if (annualFeesList == null) {
            annualFeesList = MutableLiveData()
            getAnnualFeeByStudentId(studentId)
        }
        return annualFeesList
    }

    fun getTransportFee(studentId: String): LiveData<List<TransportFees>>? {
        if (transportFee == null) {
            transportFee = MutableLiveData()
            getTransportFeeByStudentId(studentId)
        }
        return transportFee


    }
}