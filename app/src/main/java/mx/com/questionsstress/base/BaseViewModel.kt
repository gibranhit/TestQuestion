package mx.com.questionsstress.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel(), CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

    fun main(work: suspend (()-> Unit)) = CoroutineScope(coroutineContext).launch {
        work()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}