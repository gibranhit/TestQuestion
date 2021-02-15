package mx.com.questionsstress.ui.selecttest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.select_test_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.adapter.SelectTestAdapter
import mx.com.questionsstress.ui.dashboard.listener.DashboardCommunication
import mx.com.questionsstress.utils.Helper
import mx.com.questionsstress.utils.extensions.toast

class SelectTestFragment : Fragment(R.layout.select_test_fragment) {

    private var communication: DashboardCommunication? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when {
            requireParentFragment() is DashboardCommunication -> {
                communication = parentFragment as DashboardCommunication
            }
            context is DashboardCommunication -> {
                communication = context
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvSelectTest.run {
            adapter = getSelectTestAdapter()
        }
    }

    private fun getSelectTestAdapter(): SelectTestAdapter = SelectTestAdapter(Helper.getListTest()) {
        val result = "result"
        // Use the Kotlin extension in the fragment-ktx artifact
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        communication?.selectTest(it)
        toast(it.name)
    }

}