package mx.com.questionsstress.ui.dashboard.listener

import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.domain.models.response.TestResponse

interface DashboardCommunication{
    fun selectTest(test: TestResponse)
    fun selectSearchTest(result: ResultResponse)
}

interface OnBackStack{
    fun onBackPressed()
}