package com.app.jcs.view.activities.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.jcs.api.apimodels.ParentLogin

class MainActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    private fun getData() {
        val data = intent.getParcelableExtra<ParentLogin>("data")
        Log.d(tag, "getData: ${data?.motherName}")
    }
}
