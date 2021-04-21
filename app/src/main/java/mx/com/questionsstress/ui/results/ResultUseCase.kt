package mx.com.questionsstress.ui.results

import mx.com.questionsstress.domain.models.request.ResultRequest
import mx.com.questionsstress.domain.remote.ServiceEndpoints

class ResultUseCase(private val endpoints: ServiceEndpoints) {
    suspend fun createResult(request: ResultRequest) = endpoints.createResults(request)
}