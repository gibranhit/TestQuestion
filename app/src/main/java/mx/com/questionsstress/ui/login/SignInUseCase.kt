package mx.com.questionsstress.ui.login

import mx.com.questionsstress.domain.models.request.UserRequest
import mx.com.questionsstress.domain.remote.ServiceEndpoints

class SignInUseCase(private val endpoints: ServiceEndpoints) {

    suspend fun signIn(request: UserRequest) = endpoints.signIn(request)
}