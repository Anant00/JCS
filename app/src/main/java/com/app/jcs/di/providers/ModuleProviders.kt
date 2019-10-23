package com.app.jcs.di.providers

import com.app.jcs.api.apiservice.Api
import com.app.jcs.utils.SessionManager
import com.app.jcs.view.activities.auth.AuthRepo
import com.app.jcs.viewmodels.AuthViewModel
import io.reactivex.disposables.CompositeDisposable

object ModuleProviders {

    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    fun provideSessionManager(): SessionManager {
        return SessionManager()
    }

    fun provideLoginRepo(api: Api, sessionManager: SessionManager): AuthRepo {
        return AuthRepo(api, sessionManager)
    }

    fun provideAuthViewModules(
        sessionManager: SessionManager,
        authRepo: AuthRepo
    ): AuthViewModel {
        return AuthViewModel(sessionManager, authRepo)
    }
}