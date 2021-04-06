package mx.com.questionsstress.domain.remote

import kotlinx.coroutines.CompletableDeferred
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseResponse<T: Any, O: Any> {

    suspend fun consume():ResponseWrapper<O> {
        val def = CompletableDeferred<ResponseWrapper<O>>()
        runCatching {
            consumeService()
        }.onSuccess {
            if (it.isSuccessful)
                def.complete(getOutcome(it.body()))
            else
                def.complete(ResponseWrapper.Error("Error:  ${it.errorBody().toString()}", it.code()))
        }.onFailure {
            when (it) {
                is HttpException -> def.complete(ResponseWrapper.Error(it.localizedMessage.orEmpty(), it.code()))
                else -> def.complete(ResponseWrapper.Error(it.localizedMessage.orEmpty()))
            }
        }
        return def.await()
    }
    protected abstract suspend fun consumeService(): Response<T>

    protected abstract fun getOutcome(body: T?): ResponseWrapper<O>

}