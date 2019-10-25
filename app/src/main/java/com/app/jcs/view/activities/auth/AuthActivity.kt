package com.app.jcs.view.activities.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.jcs.R
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.view.activities.main.MainActivity
import com.app.jcs.viewmodels.AuthViewModel
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {
    private val tag by lazy {
        javaClass.simpleName
    }
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        loginUser()
        observeState()
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

    private fun observeState() {
        authViewModel.observeAuthState().observe(this, Observer {
            when (it?.status) {
                AuthResource.AuthStatus.LOADING -> {
                    Log.d(tag, "Loading")
                }
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    it.data?.let { data ->
                        navigateToMainActivity(data)
                    }
                }
                AuthResource.AuthStatus.ERROR -> Log.d(tag, "error: ${it.message}")
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> Log.d(tag, "failed: User logged out")
                null -> Log.d(tag, "null")
            }
        })
    }

    private fun navigateToMainActivity(parentLogin: ParentLogin) {
        startActivity(Intent(this, MainActivity::class.java).putExtra("data", parentLogin))
        overridePendingTransition(0, 0)
    }

}
