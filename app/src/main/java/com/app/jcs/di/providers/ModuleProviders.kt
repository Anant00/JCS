package com.app.jcs.di.providers

import com.app.jcs.api.apiservice.Api
import com.app.jcs.viewmodels.AuthViewModel
import io.reactivex.disposables.CompositeDisposable

object ModuleProviders {

    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    fun provideAuthViewModules(api: Api, disposable: CompositeDisposable): AuthViewModel {
        return AuthViewModel(api, disposable)
    }
}