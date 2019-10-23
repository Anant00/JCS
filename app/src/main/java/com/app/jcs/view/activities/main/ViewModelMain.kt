package com.app.jcs.view.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.jcs.api.apimodels.AdmissionFee
import com.app.jcs.api.apimodels.StudentDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ViewModelMain(
    private val mainRepo: MainRepo,
    private val disposable: CompositeDisposable
) : ViewModel() {

    private var studentDetail: MutableLiveData<List<StudentDetail>>? = null
    private var admissionFee: MutableLiveData<List<AdmissionFee>>? = null

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

    fun getStudentDetail(parentId: String): MutableLiveData<List<StudentDetail>>? {
        if (studentDetail == null) {
            studentDetail = MutableLiveData()
            getStudentDetailByParentId(parentId)
        }
        return studentDetail
    }

    fun getAdmissionFee(studentId: String): MutableLiveData<List<AdmissionFee>>? {
        if (admissionFee == null) {
            admissionFee = MutableLiveData()
            getAdmissionFeeByStudentId(studentId)
        }
        return admissionFee
    }
}