package com.app.jcs.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.jcs.R

class MainActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
