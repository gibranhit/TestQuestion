package mx.com.questionsstress.ui.results

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.result_fragment.*
import mx.com.questionsstress.R


class ResultFragment : Fragment(R.layout.result_fragment) {

    private var count = 0
    private var title: String? = ""

    private val colors = arrayOf("#03A9F4", "#5CEF00" ,"#FFEB3B","#ef6c00", "#F44336")

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
            findNavController().popBackStack(R.id.dashboardFragment, true)
        }
        progressHorizontal.progress = count
        progressHorizontal.progressDrawable.setTint(
                resources.getColor(getColorProgress()))
    }

    private fun getColor(): String = when {
        count <= 22 -> colors[0]
        count <= 32 -> colors[1]
        count <= 48 -> colors[2]
        count <= 60 -> colors[3]
        else -> colors[4]
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
        else -> "Busca ayuda"
    }

}