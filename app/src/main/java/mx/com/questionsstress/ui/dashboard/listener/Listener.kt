package mx.com.questionsstress.ui.dashboard.listener

import mx.com.questionsstress.ui.model.ResultTest
import mx.com.questionsstress.ui.model.Test

interface DashboardCommunication{
    fun selectTest(test: Test)
    fun selectSearchTest(result: ResultTest)
}