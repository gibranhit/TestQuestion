package mx.com.questionsstress.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.ui.dashboard.listener.DashboardCommunication
import mx.com.questionsstress.ui.dashboard.listener.OnBackStack
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

    override fun selectSearchTest(result: ResultResponse) {
        val bundle = Bundle().apply {
            putInt("count", result.score)
            putString("title", result.title)
        }
        findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_dashboardFragment_to_resultFragment, bundle)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)?.let { nav ->
            val currentFragment = nav.childFragmentManager.fragments[0]
            val navController =  nav.navController
            if (currentFragment is OnBackStack){
                (currentFragment as OnBackStack).onBackPressed()
            } else if (!navController.popBackStack()) {
                navController.popBackStack()
            }
        }
    }
}