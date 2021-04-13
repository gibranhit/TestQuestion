package mx.com.questionsstress.ui.teststress

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_question.*
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.response.QuestionResponse
import mx.com.questionsstress.ui.adapter.PainLevelAdapter
import mx.com.questionsstress.ui.teststress.listener.BaseOnClickListener
import mx.com.questionsstress.utils.Helper.getPainLevelList

class QuestionFragment : Fragment(R.layout.fragment_question) {
    private var param1: QuestionResponse? = null

    var listener: BaseOnClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        fun newInstance(param1: QuestionResponse) =
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
        tvTitleQuestion.text = param1?.title
        val resId = resources.getIdentifier(param1?.image, "drawable",
            requireContext().packageName)
        Glide.with(requireContext()).load(resId)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivSelectTest)
        rvPainLevel.apply {
            adapter = PainLevelAdapter(getPainLevelList()) { pain, position ->
                listener?.onSelectItem(pain, position)
            }
        }
    }

}