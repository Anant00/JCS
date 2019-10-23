package com.app.jcs.view.activities.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.jcs.R
import com.app.jcs.viewmodels.AuthViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class AuthActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }
    private val authViewModel: AuthViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginUser()
    }

    private fun loginUser() {
        btnLogin.setOnClickListener {
            if (!tvPassword.text.isNullOrEmpty() && !tvUsername.text.isNullOrEmpty()) {
                authViewModel.loginUser(tvUsername.text.toString(), tvPassword.text.toString())
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show()
            }
        }
    }
}
