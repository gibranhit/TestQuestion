package mx.com.questionsstress.domain.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import mx.com.questionsstress.domain.remote.HttpInterceptor.createOkHttpInterceptor
import mx.com.questionsstress.domain.remote.ServiceEndpoints
import mx.com.questionsstress.ui.login.SignInUseCase
import mx.com.questionsstress.ui.login.SignInViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val ApplicationModule = module {

    factory {
        SignInUseCase(get())
    }

    viewModel {
        SignInViewModel(get())
    }
}


val NetworkModule = module {

    single {
        OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(createOkHttpInterceptor())
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("http://192.168.0.3:3000/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }

    single { get<Retrofit>().create(ServiceEndpoints::class.java) }
}