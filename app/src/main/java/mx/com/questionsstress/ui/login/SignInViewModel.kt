package mx.com.questionsstress.ui.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.com.questionsstress.base.BaseViewModel
import mx.com.questionsstress.domain.models.request.UserRequest
import mx.com.questionsstress.domain.models.response.UserResponse
import mx.com.questionsstress.utils.Event
import retrofit2.HttpException

class SignInViewModel(private val useCase: SignInUseCase) : BaseViewModel() {

    private var _user = MutableStateFlow<Event<LoginUiState>>(Event(LoginUiState.Empty))
    val user: StateFlow<Event<LoginUiState>>
        get() = _user


    fun signIn(userRequest: UserRequest){
        _user.value = Event(LoginUiState.Loading)
        main {
            runCatching {
                useCase.signIn(userRequest)
            }.onSuccess {
                _user.value = Event(LoginUiState.Success(it))
            }.onFailure {
               if ( it is HttpException){
                   when (it.code()) {
                      403 -> _user.value = Event(LoginUiState.Error("Usuario o contraseña incorrecta"))
                   }
               }else  {
                   _user.value = Event(LoginUiState.Error(it.localizedMessage.orEmpty()))
               }
            }
        }
    }

    sealed class LoginUiState {
        object Empty: LoginUiState()
        object Loading: LoginUiState()
        data class Success(val response: UserResponse): LoginUiState()
        data class Error(val message: String): LoginUiState()
    }

}

