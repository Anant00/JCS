package com.app.jcs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.utils.SessionManager
import com.app.jcs.view.activities.auth.AuthResource
import com.app.jcs.view.activities.auth.LoginRepo

class AuthViewModel(
    private val sessionManager: SessionManager,
    private val loginRepo: LoginRepo
) : ViewModel() {

    private val tag by lazy {
        javaClass.simpleName
    }

    fun loginUser(username: String, password: String) {
        loginRepo.loginUser(username, password)
    }

    fun observeAuthState(): LiveData<AuthResource<ParentLogin>> {
        return sessionManager.getAuthUser()
    }
}