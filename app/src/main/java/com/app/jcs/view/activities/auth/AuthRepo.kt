package com.app.jcs.view.activities.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.api.apiservice.Api
import com.app.jcs.utils.SessionManager
import io.reactivex.schedulers.Schedulers

class AuthRepo(private var api: Api, private var sessionManager: SessionManager) {
    fun loginUser(userId: String, password: String) {
        sessionManager.authenticateWithId(queryUserId(userId, password))
    }

    private fun queryUserId(userId: String, password: String): LiveData<AuthResource<ParentLogin>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.login(userId, password)
                .onErrorReturn {
                    val errorUser = ParentLogin()
                    errorUser.id = -1
                    errorUser
                }
                .map {
                    if (it.id == -1 || it.id == null) {
                        AuthResource.error("Could not authenticate", it)
                    } else {
                        AuthResource.authenticated("Login Successful", it)
                    }
                }
                .subscribeOn(Schedulers.io())
        )
    }
}
