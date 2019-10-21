package com.app.jcs.di.modules

import com.app.jcs.api.retrofitBuilder.RetrofitBuilder
import com.app.jcs.di.providers.ModuleProviders.provideAuthViewModules
import com.app.jcs.di.providers.ModuleProviders.provideCompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var retrofitModule = module {
    single { RetrofitBuilder.provideOkHttpClient() }
    single { RetrofitBuilder.provideRetrofitInstance(get()) }
}

var authViewModule = module {
    single { provideCompositeDisposable() }
    viewModel { provideAuthViewModules(get(), get()) }
}