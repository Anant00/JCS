package com.app.jcs.di.modules

import com.app.jcs.api.retrofitBuilder.RetrofitBuilder.provideOkHttpClient
import com.app.jcs.api.retrofitBuilder.RetrofitBuilder.provideRetrofitInstance
import com.app.jcs.di.providers.ModuleProviders.provideAuthViewModules
import com.app.jcs.di.providers.ModuleProviders.provideCompositeDisposable
import com.app.jcs.di.providers.ModuleProviders.provideLoginRepo
import com.app.jcs.di.providers.ModuleProviders.provideSessionManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var retrofitModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofitInstance(get()) }
}

var authViewModule = module {
    single { provideCompositeDisposable() }
    single { provideSessionManager() }
    single { provideLoginRepo(get(), get()) }
    viewModel { provideAuthViewModules(get(), get()) }
}