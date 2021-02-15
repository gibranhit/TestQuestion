package mx.com.questionsstress.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.dashboard.listener.DashboardCommunication
import mx.com.questionsstress.ui.model.ResultTest
import mx.com.questionsstress.ui.model.Test

class MainActivity : AppCompatActivity(), DashboardCommunication {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun selectTest(test: Test) {
        val bundle = Bundle().apply { putParcelable("test", test) }
        findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_dashboardFragment_to_testStressFragment, bundle)
    }

    override fun selectSearchTest(result: ResultTest) {
        val bundle = Bundle().apply {
            putInt("count", result.total)
            putString("title", result.title)
        }
        findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_dashboardFragment_to_resultFragment, bundle)
    }
}