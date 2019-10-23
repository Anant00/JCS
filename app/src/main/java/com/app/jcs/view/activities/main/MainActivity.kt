package com.app.jcs.view.activities.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.jcs.api.apimodels.ParentLogin
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }
    private val viewModelMain: ViewModelMain by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    private fun getData() {
        val data = intent.getParcelableExtra<ParentLogin>("data")
        getStudentDetailByParentId(data?.id.toString())
    }

    private fun getStudentDetailByParentId(parentId: String) {
        viewModelMain.getStudentDetail(parentId)?.observe(this, Observer {
            Log.d(tag, "student detail : ${it[0].name} ")
        })
    }
}
