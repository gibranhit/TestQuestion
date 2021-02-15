package mx.com.questionsstress.ui.teststress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.test_stress_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.adapter.QuestionsViewPagerAdapter
import mx.com.questionsstress.ui.model.PainLevel
import mx.com.questionsstress.ui.model.QuestionTest
import mx.com.questionsstress.ui.model.Test
import mx.com.questionsstress.ui.teststress.listener.BaseOnClickListener
import mx.com.questionsstress.utils.Helper.getFragments
import mx.com.questionsstress.utils.Helper.getListQuestionStress

class TestStressFragment : Fragment() {

    private var test: Test? = null

    private var listQuestion: MutableList<QuestionTest> =  mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_stress_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            test = it.getParcelable("test")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpTitle()
        setUpViewPager()
    }

    private fun setUpTitle() {
        test?.let {
            tvTitle.text = it.name
        }
    }

    private fun setUpViewPager() {
        val questions = getListQuestionStress()
        val questionsFragments = getFragments(questions)
        vpQuestions.apply {
            isUserInputEnabled = false
            adapter = QuestionsViewPagerAdapter(this@TestStressFragment, questionsFragments)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    movedFragment(position, questionsFragments)
                    super.onPageSelected(position)
                }
            })
        }
    }

    private fun movedFragment(positionFragment: Int, list: MutableList<Fragment>){
        val questionFragment = list[positionFragment] as QuestionFragment
        questionFragment.listener = object :BaseOnClickListener {
            override fun onSelectItem(item: Any, positionQuestion: Int) {
                val painLevel = item as PainLevel
                setQuestion(painLevel, positionFragment)
                if (vpQuestions.currentItem == list.size -1){
                    val count = listQuestion.map { it.level }.sum()
                    val bundle = Bundle().apply {
                        putInt("count", count)
                        putString("title", test?.name)
                    }
                    findNavController().navigate(R.id.action_testStressFragment_to_resultFragment, bundle)
                }
                vpQuestions.currentItem = positionFragment + 1
            }
        }
    }

    private fun setQuestion(painLevel: PainLevel, positionFragment: Int) {
        val findQuestion = findQuestion(positionFragment)
        if (findQuestion) {
            listQuestion[positionFragment] = QuestionTest(positionFragment, painLevel.level)
        } else {
            listQuestion.add(QuestionTest(positionFragment, painLevel.level))
        }
    }

    private fun findQuestion(positionFragment: Int): Boolean = listQuestion.any { it.answer == positionFragment }

}