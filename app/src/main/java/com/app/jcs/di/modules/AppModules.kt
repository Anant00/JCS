package com.app.jcs.di.modules

import com.app.jcs.api.retrofitBuilder.RetrofitBuilder
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

var retrofitModule = module {

    single { provideCompositeDisposable() }
    single { RetrofitBuilder.provideOkHttpClient() }
    single { RetrofitBuilder.provideRetrofitInstance(get()) }
}

private fun provideCompositeDisposable(): CompositeDisposable {
    return CompositeDisposable()
}