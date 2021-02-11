package mx.com.questionsstress.ui.selecttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.select_test_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.adapter.SelectTestAdapter
import mx.com.questionsstress.utils.Helper
import mx.com.questionsstress.utils.extensions.toast

class SelectTestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvSelectTest.run {
            adapter = getSelectTestAdapter()
        }
    }

    private fun getSelectTestAdapter(): SelectTestAdapter = SelectTestAdapter(Helper.getListTest()) {
        val bundle = Bundle().apply { putParcelable("test", it) }
        findNavController().navigate(R.id.action_selectTestFragment_to_testStressFragment, bundle)
        toast(it.name)
    }

}