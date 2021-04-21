package mx.com.questionsstress.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.questionsstress.base.BaseViewModel
import mx.com.questionsstress.domain.models.request.ResultRequest
import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.domain.remote.ProcessStep

class ResultViewModel(private val useCase: ResultUseCase) : BaseViewModel() {
    private var _result = MutableLiveData<ResultResponse>()
    val result: LiveData<ResultResponse>
        get() = _result

    private val _step = MutableLiveData<ProcessStep>()
    val step: LiveData<ProcessStep>
        get() = _step

    fun createResult(request: ResultRequest) = viewModelScope.launch {
        _step.postValue(ProcessStep.Loading)
        runCatching {
            useCase.createResult(request)
        }.onSuccess {
            _result.value = it
            _step.postValue(ProcessStep.Finished)
        }.onFailure {
            _step.postValue(ProcessStep.Error(it.localizedMessage.orEmpty()))
        }
    }
}