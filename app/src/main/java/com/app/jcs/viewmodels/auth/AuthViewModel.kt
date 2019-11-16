package com.app.jcs.viewmodels.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.utils.SessionManager
import com.app.jcs.view.activities.auth.AuthRepo
import com.app.jcs.view.activities.auth.AuthResource

class AuthViewModel(
    sessionManager: SessionManager,
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _authStatus: LiveData<AuthResource<ParentLogin>> = sessionManager.getAuthUser()

    fun loginUser(username: String, password: String) {
        authRepo.loginUser(username, password)
    }

    fun observeAuthState(): LiveData<AuthResource<ParentLogin>> {
        return _authStatus
    }
}
