package mx.com.questionsstress.ui.teststress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.com.questionsstress.base.BaseViewModel
import mx.com.questionsstress.domain.models.request.UserRequest
import mx.com.questionsstress.domain.models.response.TestResponse
import mx.com.questionsstress.domain.remote.ProcessStep

class TestStressViewModel(private val useCase: TestStressUseCase) : BaseViewModel() {

    private var _test = MutableLiveData<TestResponse>()
    val test: LiveData<TestResponse>
        get() = _test

    private val _step = MutableLiveData<ProcessStep>()
    val step: LiveData<ProcessStep>
        get() = _step


    fun getQuestions(){
        _step.postValue(ProcessStep.Loading)
        main {
            runCatching {
                useCase.getQuestions()
            }.onSuccess {
                _test.postValue(it)
                _step.postValue(ProcessStep.Finished)
            }.onFailure {
                _step.postValue(ProcessStep.Error(it.localizedMessage.orEmpty()))
            }
        }
    }

}