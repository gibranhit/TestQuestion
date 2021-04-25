package mx.com.questionsstress.ui.selecttest

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import kotlinx.android.synthetic.main.select_test_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.response.TestResponse
import mx.com.questionsstress.domain.remote.ProcessStep
import mx.com.questionsstress.ui.adapter.SelectTestAdapter
import mx.com.questionsstress.ui.dashboard.listener.DashboardCommunication
import mx.com.questionsstress.utils.extensions.configProgressBar
import mx.com.questionsstress.utils.extensions.observe
import mx.com.questionsstress.utils.extensions.snack
import mx.com.questionsstress.utils.extensions.toast
import org.koin.android.viewmodel.ext.android.viewModel

class SelectTestFragment : Fragment(R.layout.select_test_fragment) {

    private var communication: DashboardCommunication? = null

    private val viewModel: SelectTestViewModel by viewModel()

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
        setUpObserver()
    }

    private fun setUpObserver() {
        observe(viewModel.step, ::stepper)
        observe(viewModel.tests, ::setUpTests)
    }

    private fun setUpTests(list: MutableList<TestResponse>) {
        rvSelectTest.run {
            adapter = getSelectTestAdapter(list)
        }
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
            is ProcessStep.Finished -> loading(false)
        }
    }

    private fun getSelectTestAdapter(list: MutableList<TestResponse>): SelectTestAdapter = SelectTestAdapter(list) {
        val result = "result"
        // Use the Kotlin extension in the fragment-ktx artifact
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        communication?.selectTest(it)
        toast(it.title)
    }

}