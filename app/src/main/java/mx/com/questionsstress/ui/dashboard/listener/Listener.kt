package mx.com.questionsstress.ui.dashboard.listener

import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.ui.model.Test

interface DashboardCommunication{
    fun selectTest(test: Test)
    fun selectSearchTest(result: ResultResponse)
}

interface OnBackStack{
    fun onBackPressed()
}