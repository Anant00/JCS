package com.app.jcs.view.activities.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.jcs.R
import com.app.jcs.api.apimodels.Fees
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.utils.DateExtractUtils
import com.app.jcs.viewmodels.main.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }
    private lateinit var studentId: String
    private lateinit var classId: String
    private lateinit var feePaidUpToDate: String
    private var admissionFeePaid: MutableLiveData<Boolean> = MutableLiveData(false)
    private var annualFeePaid: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    private fun getData() {
        val data = intent.getParcelableExtra<ParentLogin>("data")
        getStudentDetailByParentId(data?.id.toString())
    }

    private fun getStudentDetailByParentId(parentId: String) {
        mainViewModel.getStudentDetail(parentId)?.observe(this, Observer {
            studentId = it[0].id.toString()
            classId = it[0].classId.toString()
            feePaidUpToDate = it[0].feePaidUpToDate.toString()
            Log.d(tag, "fee paid upto date:$feePaidUpToDate")
            getAdmissionFeeByStudentId(studentId)
            getAnnualFee(studentId)
            getTransportFeeByStudentId(studentId)
            date()
            getFeeByClassId(classId)
        })
    }

    private fun getAdmissionFeeByStudentId(studentId: String) {
        mainViewModel.getAdmissionFee(studentId)?.observe(this, Observer {
            if (it[0].id == null) {
                Log.d(tag, "Admission Fee not paid for student ID $studentId:")
            } else {
                admissionFeePaid.value = true
                Log.d(tag, " admission fee paid already for student id $studentId:")
            }
        })
    }

    private fun getFeeByClassId(classId: String) {
        mainViewModel.getFee(classId)?.observe(this, Observer {
            Log.d(tag, "fee amount is: ${it[0].feePerMonth} ")
            setData(it)
        })
    }

    private fun getAnnualFee(studentId: String) {
        mainViewModel.getAnnualFee(studentId)?.observe(this, Observer {
            if (it[0].id == null) {
                Log.d(tag, "Annual Fee not paid for student ID $studentId:")
            } else {
                annualFeePaid.value = true
                Log.d(tag, " Annual fee paid already for student id $studentId:")
            }
        })
    }

    private fun date() {
        val day = DateExtractUtils.extractDateFromMonth(feePaidUpToDate)
        Log.d(tag, "date is : $day")
    }

    private fun getTransportFeeByStudentId(studentId: String) {
        mainViewModel.getTransportFee(studentId)?.observe(this, Observer {
            Log.d(tag, "transport fee: ${it[0].userId} ")
            if (it[0].userId != null) {
                Log.d(tag, "transport fee: ${it[0].userId} ")
            } else {
                Log.d(tag, "transport fee not paid: ")
            }
        })
    }

    private fun setData(list: List<Fees>) {
        admissionFeePaid.observe(this, Observer {
            if (!it) {
                tvAdmission.text = "Admission:  ${list[0].admission}"
            } else {
                tvAdmission.text = "Admission:  0"
            }
        })
        annualFeePaid.observe(this, Observer {
            if (!it) {
                tvAnnual.text = "Annual:  ${list[0].annual}"
            } else {
                tvAnnual.text = "Annual:  0"
            }
        })
        progress_circular.visibility = View.GONE
    }
}
