package mx.com.questionsstress.ui.teststress

import mx.com.questionsstress.domain.remote.ServiceEndpoints

class TestStressUseCase(private val endpoints: ServiceEndpoints) {
    suspend fun getQuestions() = endpoints.getQuestions()
}