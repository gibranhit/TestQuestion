package mx.com.questionsstress.base

import android.app.Application
import mx.com.questionsstress.domain.di.ApplicationModule
import mx.com.questionsstress.domain.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Start koin modules
        startKoin {
            androidContext(this@MyTestApplication)
            modules(listOf(NetworkModule, ApplicationModule))
        }
    }
}