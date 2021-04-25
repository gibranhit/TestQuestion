package mx.com.questionsstress.domain.remote

import mx.com.questionsstress.domain.models.request.ResultRequest
import mx.com.questionsstress.domain.models.request.UserRequest
import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.domain.models.response.TestResponse
import mx.com.questionsstress.domain.models.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceEndpoints {
    @POST("v1/api/user/")
    suspend fun signIn(@Body body: UserRequest): UserResponse

    @GET("v1/api/test/0")
    suspend fun getQuestions(): TestResponse

    @GET("v1/api/test/")
    suspend fun getTest(): MutableList<TestResponse>

    @POST("v1/api/result")
    suspend fun createResults(@Body body: ResultRequest): ResultResponse

    @GET("v1/api/result/{correo}")
    suspend fun getResults(@Path(value = "correo", encoded = true) id: String): MutableList<ResultResponse>

}