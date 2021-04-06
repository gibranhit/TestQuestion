package mx.com.questionsstress.domain.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpInterceptor {

    fun createOkHttpInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

}