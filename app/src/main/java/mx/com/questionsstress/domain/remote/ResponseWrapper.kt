package mx.com.questionsstress.domain.remote

sealed class ResponseWrapper<out T>{
    data class Success<out T>(val data: T) : ResponseWrapper<T>()
    data class Error(val message: String, val code: Int = 0) : ResponseWrapper<Nothing>()
}