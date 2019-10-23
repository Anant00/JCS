package com.app.jcs.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.app.jcs.api.apimodels.ParentLogin
import com.app.jcs.view.activities.auth.AuthResource

class SessionManager {
    private val tag by lazy {
        javaClass.simpleName
    }
    private var cachedUser: MediatorLiveData<AuthResource<ParentLogin>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<ParentLogin>>) {
        Log.d(tag, "Logging in")
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source) { userAuthResource ->
            cachedUser.value = userAuthResource
            cachedUser.removeSource(source)
        }
    }

    fun getAuthUser(): MediatorLiveData<AuthResource<ParentLogin>> {
        return cachedUser
    }

}