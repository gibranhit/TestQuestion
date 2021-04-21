package mx.com.questionsstress.ui.dashboard.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.com.questionsstress.base.BaseViewModel
import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.domain.remote.ProcessStep

class SearchViewModel(private val useCase: SearchUseCase) : BaseViewModel() {
    private var _results = MutableLiveData<MutableList<ResultResponse>>()
    val results: LiveData<MutableList<ResultResponse>>
        get() = _results

    private val _step = MutableLiveData<ProcessStep>()
    val step: LiveData<ProcessStep>
        get() = _step

    fun getResults(email: String ) = main {
        _step.postValue(ProcessStep.Loading)
        runCatching {
            useCase.getResults(email)
        }.onSuccess {
            _results.value = it
            _step.postValue(ProcessStep.Finished)
        }.onFailure {
            _step.postValue(ProcessStep.Error(it.localizedMessage.orEmpty()))
        }
    }
}