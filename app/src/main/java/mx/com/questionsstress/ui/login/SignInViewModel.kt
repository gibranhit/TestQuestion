package mx.com.questionsstress.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.com.questionsstress.base.BaseViewModel
import mx.com.questionsstress.domain.models.request.UserRequest
import mx.com.questionsstress.domain.models.response.UserResponse
import mx.com.questionsstress.domain.remote.ProcessStep
import retrofit2.HttpException

class SignInViewModel(private val useCase: SignInUseCase) : BaseViewModel() {

    private var _user = MutableLiveData<UserResponse>()
    val user: LiveData<UserResponse>
        get() = _user

    private val _step = MutableLiveData<ProcessStep>()
    val step: LiveData<ProcessStep>
        get() = _step

    fun signIn(userRequest: UserRequest){
        _step.postValue(ProcessStep.Loading)
        main {
            runCatching {
                useCase.signIn(userRequest)
            }.onSuccess {
                _user.postValue(it)
                _step.postValue(ProcessStep.Finished)
            }.onFailure {
               if ( it is HttpException){
                   when (it.code()){
                      403 ->_step.postValue(ProcessStep.Error("Usuario o contraseña incorrecta"))
                   }
               }else  {
                   _step.postValue(ProcessStep.Error(it.localizedMessage.orEmpty()))

               }
            }
        }
    }

}