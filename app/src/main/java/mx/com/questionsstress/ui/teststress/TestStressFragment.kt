package mx.com.questionsstress.ui.teststress

import android.app.Dialog
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
import mx.com.questionsstress.databinding.TestStressFragmentBinding
import mx.com.questionsstress.domain.models.response.TestResponse
import mx.com.questionsstress.domain.remote.ProcessStep
import mx.com.questionsstress.ui.adapter.QuestionsViewPagerAdapter
import mx.com.questionsstress.ui.login.SignInViewModel
import mx.com.questionsstress.ui.model.PainLevel
import mx.com.questionsstress.ui.model.QuestionTest
import mx.com.questionsstress.ui.model.Test
import mx.com.questionsstress.ui.results.ResultFragment
import mx.com.questionsstress.ui.teststress.listener.BaseOnClickListener
import mx.com.questionsstress.utils.Helper.getFragments
import mx.com.questionsstress.utils.Helper.getListQuestionStress
import mx.com.questionsstress.utils.extensions.configProgressBar
import mx.com.questionsstress.utils.extensions.observe
import mx.com.questionsstress.utils.extensions.snack
import org.koin.android.viewmodel.ext.android.viewModel

class TestStressFragment : Fragment() {

    private val viewModel: TestStressViewModel by viewModel()

    private var test: TestResponse? = null

    private val dialog: Dialog by lazy { configProgressBar(R.color.purple_200) }

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
        setUpObserver()
    }

    private fun setUpTitle() {
        test?.let {
            tvTitle.text = it.title
            setUpViewPager(it)
        }
    }

    private fun setUpObserver() {
        observe(viewModel.step, ::stepper)
        observe(viewModel.test, ::setUpViewPager)
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

    private fun setUpViewPager(test: TestResponse ) {
        val questions = test.questions
        val maxQuestions = questions.size
        tvAnswerCount.text = "Pregunta 1/$maxQuestions"
        val questionsFragments = getFragments(questions)
        vpQuestions.apply {
            isUserInputEnabled = false
            adapter = QuestionsViewPagerAdapter(this@TestStressFragment, questionsFragments)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    tvAnswerCount.text = "Pregunta ${position + 1}/$maxQuestions"
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
                        putInt(ResultFragment.ARG_COUNT, count)
                        putString(ResultFragment.ARG_TITLE, test?.title)
                        putInt(ResultFragment.ARG_TYPE, test?.type?: 0)
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