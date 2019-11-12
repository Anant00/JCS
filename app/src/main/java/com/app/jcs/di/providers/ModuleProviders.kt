package com.app.jcs.di.providers

import com.app.jcs.api.apiservice.Api
import com.app.jcs.utils.SessionManager
import com.app.jcs.view.activities.auth.AuthRepo
import com.app.jcs.view.activities.main.MainRepo
import com.app.jcs.viewmodels.auth.AuthViewModel
import com.app.jcs.viewmodels.main.MainViewModel
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

    fun provideMainViewModel(
        mainRepo: MainRepo,
        disposable: CompositeDisposable
    ): MainViewModel {
        return MainViewModel(mainRepo, disposable)
    }

    fun provideMainRepo(api: Api): MainRepo {
        return MainRepo(api)
    }
}
