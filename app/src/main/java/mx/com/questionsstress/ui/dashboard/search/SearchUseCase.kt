package mx.com.questionsstress.ui.dashboard.search

import mx.com.questionsstress.domain.remote.ServiceEndpoints

class SearchUseCase(private val endpoints: ServiceEndpoints) {
    suspend fun getResults(email: String) = endpoints.getResults(email)
}