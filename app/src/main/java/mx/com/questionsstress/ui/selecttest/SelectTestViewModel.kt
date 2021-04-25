package mx.com.questionsstress.ui.selecttest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.questionsstress.base.BaseViewModel
import mx.com.questionsstress.domain.models.response.TestResponse
import mx.com.questionsstress.domain.remote.ProcessStep

class SelectTestViewModel(private val useCase: SelectTestUseCase) : BaseViewModel() {
    private var _tests = MutableLiveData<MutableList<TestResponse>>()
    val tests: LiveData<MutableList<TestResponse>>
        get() = _tests

    private val _step = MutableLiveData<ProcessStep>()
    val step: LiveData<ProcessStep>
        get() = _step

    init {
        getTests()
    }

    private fun getTests() = viewModelScope.launch {
        _step.postValue(ProcessStep.Loading)
        runCatching {
            useCase.getTests()
        }.onSuccess {
            _tests.postValue(it)
            _step.postValue(ProcessStep.Finished)
        }.onFailure {
            _step.postValue(ProcessStep.Error(it.localizedMessage.orEmpty()))
        }
    }
}