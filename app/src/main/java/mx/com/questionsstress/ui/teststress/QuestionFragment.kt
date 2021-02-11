package mx.com.questionsstress.ui.teststress

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_question.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.adapter.PainLevelAdapter
import mx.com.questionsstress.ui.teststress.listener.BaseOnClickListener
import mx.com.questionsstress.utils.Helper.getPainLevelList
import mx.com.questionsstress.utils.extensions.toast


private const val ARG_PARAM1 = "param1"

class QuestionFragment : Fragment() {

    private var param1: String? = null

    var listener: BaseOnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpData()
    }

    private fun setUpData() {
        tvTitleQuestion.text = param1
        rvPainLevel.apply {
            adapter = PainLevelAdapter(getPainLevelList()) { pain, position ->
                listener?.onSelectItem(pain, position)
            }
        }
    }


}