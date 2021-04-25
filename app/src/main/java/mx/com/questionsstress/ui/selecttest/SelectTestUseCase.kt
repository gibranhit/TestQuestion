package mx.com.questionsstress.ui.selecttest

import mx.com.questionsstress.domain.remote.ServiceEndpoints

class SelectTestUseCase(private val endpoints: ServiceEndpoints) {

    suspend fun getTests() = endpoints.getTest()
}