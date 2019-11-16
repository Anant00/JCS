package com.app.jcs.view.activities.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.jcs.R
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.view.activities.main.MainActivity
import com.app.jcs.viewmodels.auth.AuthViewModel
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

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
                    progressBar.visibility = VISIBLE
                    btnLogin.isEnabled = false
                }
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    it.data?.let { data ->
                        navigateToMainActivity(data)
                    }
                }
                AuthResource.AuthStatus.ERROR -> {
                    progressBar.visibility = INVISIBLE
                    btnLogin.isEnabled = true
                    Log.d(TAG, "error: ${it.message}")
                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> Log.d(TAG, "failed: User logged out")
                null -> Log.d(TAG, "null")
            }
        })
    }

    private fun navigateToMainActivity(parentLogin: ParentLogin) {
        startActivity(Intent(this, MainActivity::class.java).putExtra("data", parentLogin))
        overridePendingTransition(0, 0)
        progressBar.visibility = INVISIBLE
        btnLogin.isEnabled = true
    }

    companion object {
        private val TAG by lazy {
            AuthActivity::class.java.simpleName
        }
    }
}
