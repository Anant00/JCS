package com.app.jcs.view.activities.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.api.apiservice.Api
import com.app.jcs.utils.SessionManager
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

class LoginRepo(var api: Api, var sessionManager: SessionManager) {
    private val tag by lazy {
        javaClass.simpleName
    }

    fun loginUser(userId: String, password: String) {
        sessionManager.authenticateWithId(queryUserId(userId, password))
    }

    private fun queryUserId(userId: String, password: String): LiveData<AuthResource<ParentLogin>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.login(userId, password)
                .onErrorReturn
                {
                    val errorUser = ParentLogin()
                    errorUser.id = -1
                    errorUser
                }
                .map(Function<ParentLogin, AuthResource<ParentLogin>> { user ->
                    if (user.id == -1) {
                        AuthResource.error("Could not authenticate", null)
                    } else AuthResource.authenticated(user)
                })
                .subscribeOn(Schedulers.io())
        )
    }
}