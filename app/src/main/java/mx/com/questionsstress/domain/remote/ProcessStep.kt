package mx.com.questionsstress.domain.remote

sealed class ProcessStep {
    object Loading: ProcessStep()
    object Finished: ProcessStep()
    data class Error(val message: String): ProcessStep()
}