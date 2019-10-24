package com.app.jcs.view.activities.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.jcs.R
import com.app.jcs.api.apimodels.ParentLogin
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }
    private lateinit var studentId: String
    private lateinit var classId: String
    private val viewModelMain: ViewModelMain by viewModel()

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
        viewModelMain.getStudentDetail(parentId)?.observe(this, Observer {
            studentId = "3"
            classId = it[0].classId.toString()
            getAdmissionFeeByStudentId(studentId)
            getFeeByClassId(classId)
            getAnnualFee(studentId)
        })
    }

    private fun getAdmissionFeeByStudentId(studentId: String) {
        viewModelMain.getAdmissionFee(studentId)?.observe(this, Observer {
            if (it[0].id == null) {
                Log.d(tag, "Admission Fee not paid for student ID $studentId:")
            } else {
                Log.d(tag, " admission fee paid already for student id $studentId:")
            }
        })
    }

    private fun getFeeByClassId(classId: String) {
        viewModelMain.getFee(classId)?.observe(this, Observer {
            Log.d(tag, "fee amount is: ${it[0].admission} ")
        })
    }

    private fun getAnnualFee(studentId: String) {
        viewModelMain.getAnnualFee(studentId)?.observe(this, Observer {
            if (it[0].id == null) {
                Log.d(tag, "Annual Fee not paid for student ID $studentId:")
            } else {
                Log.d(tag, " Annual fee paid already for student id $studentId:")
            }
        })
    }

}
