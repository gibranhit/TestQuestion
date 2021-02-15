package mx.com.questionsstress.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.dashboard_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.dashboard.listener.DashboardCommunication
import mx.com.questionsstress.ui.model.Test

class DashboardFragment : Fragment(R.layout.dashboard_fragment) {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    var result : String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            result = bundle.getString("bundleKey")
        }
    }


    private fun setUpNavigation() {
        (childFragmentManager.findFragmentById(R.id.navHostFragment) as? NavHostFragment)?.let { nav ->
            NavigationUI.setupWithNavController(bottomNavigationView, nav.navController)
            bottomNavigationView.setOnNavigationItemSelectedListener {
                if (isValidatedDestination(controller = nav.navController, destination = it.itemId))
                    nav.navController.navigate(it.itemId)
                true
            }
        }
    }

    private fun isValidatedDestination(controller: NavController, destination: Int): Boolean =
        destination != controller.currentDestination?.id

}