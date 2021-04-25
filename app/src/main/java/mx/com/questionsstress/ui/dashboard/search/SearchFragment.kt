package mx.com.questionsstress.ui.dashboard.search

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.search_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.domain.remote.ProcessStep
import mx.com.questionsstress.ui.dashboard.listener.DashboardCommunication
import mx.com.questionsstress.ui.dashboard.search.adapter.SearchTestAdapter
import mx.com.questionsstress.ui.login.SignInFragment
import mx.com.questionsstress.utils.extensions.configProgressBar
import mx.com.questionsstress.utils.extensions.getDataString
import mx.com.questionsstress.utils.extensions.observe
import mx.com.questionsstress.utils.extensions.snack
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    private var communication: DashboardCommunication? = null

    private val viewModel: SearchViewModel by viewModel()

    private val dialog: Dialog by lazy { configProgressBar(R.color.purple_200) }

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
        tvTitle.text = "Resultado de tests"
        observe(viewModel.results, ::setUpTest)
        observe(viewModel.step, ::stepper)
        val email = getDataString(SignInFragment.NAME_PREFERENCES_TEST, SignInFragment.KEY_EMAIL)
        viewModel.getResults(email)
    }

    private fun loading(loaded: Boolean) {
        if (loaded) dialog.show() else dialog.dismiss()
    }

    private fun stepper(step: ProcessStep) {
        when(step) {
            is ProcessStep.Loading -> loading(true)
            is ProcessStep.Error -> {
                loading(false)
                snack(step.message)
            }
            is ProcessStep.Finished -> {
                loading(false)
            }
        }
    }

    private fun setUpTest(results: MutableList<ResultResponse>) {
        tvEmptyList.visibility  = if (results.isEmpty()) View.VISIBLE else View.GONE
        rvSearchTest.apply {
            adapter = SearchTestAdapter(results){
                communication?.selectSearchTest(it)
            }
            visibility = if (results.isEmpty()) View.GONE else View.VISIBLE
        }
    }

}