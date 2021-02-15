package mx.com.questionsstress.ui.teststress

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_question.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.adapter.PainLevelAdapter
import mx.com.questionsstress.ui.model.Answer
import mx.com.questionsstress.ui.teststress.listener.BaseOnClickListener
import mx.com.questionsstress.utils.Helper.getPainLevelList

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private var param1: Answer? = null

    var listener: BaseOnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        fun newInstance(param1: Answer) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpData()
    }

    private fun setUpData() {
        tvTitleQuestion.text = param1?.answer
        Glide.with(requireContext()).load(param1?.image).into(ivSelectTest)
        rvPainLevel.apply {
            adapter = PainLevelAdapter(getPainLevelList()) { pain, position ->
                listener?.onSelectItem(pain, position)
            }
        }
    }

}