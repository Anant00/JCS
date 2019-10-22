package com.app.jcs.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.jcs.R
import com.app.jcs.viewmodels.AuthViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }
    private val authViewModel: AuthViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authViewModel.loginUser()
    }
}
