package mx.com.questionsstress.domain.remote

import mx.com.questionsstress.domain.models.request.UserRequest
import mx.com.questionsstress.domain.models.response.TestResponse
import mx.com.questionsstress.domain.models.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceEndpoints {
    @POST("v1/api/user/")
    suspend fun signIn(@Body body: UserRequest): UserResponse

    @GET("v1/api/test/0")
    suspend fun getQuestions(): TestResponse

}