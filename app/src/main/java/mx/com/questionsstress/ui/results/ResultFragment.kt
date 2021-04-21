package mx.com.questionsstress.ui.results

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.item_search.view.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.result_fragment.*
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.request.ResultRequest
import mx.com.questionsstress.domain.models.response.ResultResponse
import mx.com.questionsstress.domain.remote.ProcessStep
import mx.com.questionsstress.ui.dashboard.listener.OnBackStack
import mx.com.questionsstress.ui.login.SignInFragment
import mx.com.questionsstress.ui.teststress.TestStressViewModel
import mx.com.questionsstress.utils.extensions.configProgressBar
import mx.com.questionsstress.utils.extensions.getDataString
import mx.com.questionsstress.utils.extensions.observe
import mx.com.questionsstress.utils.extensions.snack
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class ResultFragment : Fragment(R.layout.result_fragment), OnBackStack {

    private val viewModel: ResultViewModel by viewModel()
    private val dialog: Dialog by lazy { configProgressBar(R.color.purple_200) }
    private var count = 0
    private var title: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            count = it.getInt("count")
            title = it.getString("title")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = title
        tvAdvice.text = getAdvice()
        tvProgressTest.text = "$count/76"
        tvResultTest.text = getTitle()
        if (count > 60)
            tvAdvice.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorRed))
        buttonFinish.setOnClickListener {
            val email = getDataString(SignInFragment.NAME_PREFERENCES_TEST, SignInFragment.KEY_EMAIL)
            val actualDate = Date()
            val date = SimpleDateFormat("dd 'de' MMMM yyyy", Locale("es", "MX")).format(actualDate)
            viewModel.createResult(ResultRequest(maxScore = 76, score = count, title = title.orEmpty(), date = date, typeTest = 0, correo = email))
        }
        progressHorizontal.progress = count
        progressHorizontal.progressDrawable.setTint(
                resources.getColor(getColorProgress()))
        observe(viewModel.step, ::stepper)
    }

    private fun getColorProgress(): Int = when {
        count <= 22 -> R.color.colorBlue
        count <= 32 -> R.color.colorGreen
        count <= 48 -> R.color.colorYellow
        count <= 60 -> R.color.colorOrange
        else -> R.color.colorRed
    }

    private fun getTitle(): String = when {
        count <= 22 -> "Sin estrés"
        count <= 32 -> "Estrés leve"
        count <= 48 -> "Estrés medio"
        count <= 60 -> "Estrés alto"
        else -> "Estrés grave"
    }

    private fun getAdvice(): String = when {
        count <= 22 -> "No existe síntoma alguno de estrés. " +
                "Tienes un buen equilibrio, continúa así y contagia a los " +
                "demás de tus estrategias de afrontamiento!"
        count <= 32 -> "Te encuentras en fase de alarma, trata de identificar el " +
                "o los factores que te causan estrés para poder " +
                "ocuparte de ellos de manera preventiva."
        count <= 48 -> "Haz conciencia de la situación en la que te encuentras " +
                "y trata de ubicar qué puedes modificar, ya que si la " +
                "situación estresante se prolonga, puedes romper tu " +
                "equilibrio entre lo laboral y lo personal. No agotes tus " +
                "resistencias!"
        count <= 60 -> "Te encuentras en una fase de agotamiento de recursos " +
                "fisiológicos con desgaste físico y mental. Esto puede " +
                "tener consecuencias más serias para tu salud. "
        else -> "Si este nivel de estrés persiste en los últimos días, " +
                "debes buscar ayuda profesional para poder sentirte mejor. "
    }

    override fun onBackPressed() {
        findNavController().popBackStack(R.id.dashboardFragment, false)
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
                snack("Tu Test se guardo correctamente.")
                onBackPressed()
            }
        }
    }

}