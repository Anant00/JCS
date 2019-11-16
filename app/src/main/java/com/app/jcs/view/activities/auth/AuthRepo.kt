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
                .subscribeOn(Schedulers.io())
                .map {
                    if (it.username != null) AuthResource.authenticated(
                        "Logged in successfully",
                        it
                    )
                    else AuthResource.error(
                        "Failed to loginUser in, please check in your username and password",
                        it
                    )
                }
                .doOnError { AuthResource.error(it.localizedMessage, it) }
                .onErrorReturn { AuthResource.error(it.localizedMessage, null) }
        )
    }
}
