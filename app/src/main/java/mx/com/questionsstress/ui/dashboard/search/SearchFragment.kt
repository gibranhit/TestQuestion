package mx.com.questionsstress.ui.dashboard.search

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.search_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.dashboard.listener.DashboardCommunication
import mx.com.questionsstress.ui.dashboard.search.adapter.SearchTestAdapter
import mx.com.questionsstress.utils.Helper.getSearchList

class SearchFragment : Fragment(R.layout.search_fragment) {

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
        setUpTest()
    }

    private fun setUpTest() {
        rvSearchTest.apply {
            adapter = SearchTestAdapter(getSearchList()){
                communication?.selectSearchTest(it)
            }
        }
    }

}