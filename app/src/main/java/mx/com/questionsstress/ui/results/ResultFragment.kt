package mx.com.questionsstress.ui.results

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.SingleValueDataSet
import com.anychart.core.gauge.pointers.Bar
import com.anychart.enums.Anchor
import com.anychart.graphics.vector.Fill
import com.anychart.graphics.vector.SolidFill
import com.anychart.graphics.vector.text.VAlign
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
        setUpChart()
        buttonFinish.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_dashboardFragment)
        }
    }

    private fun setUpChart(){

        val circularGauge = AnyChart.circular()
        circularGauge.data(
            SingleValueDataSet(
                arrayOf(
                    "23",
                    "34",
                    count.toString(),
                    "93",
                    "12",
                    "76"
                )
            )
        )
        circularGauge.fill("#fff")
            .stroke(null)
            .padding(0.0, 0.0, 0.0, 0.0)
            .margin(100.0, 100.0, 100.0, 100.0)
        circularGauge.startAngle(0.0)
        circularGauge.sweepAngle(270.0)

        val xAxis = circularGauge.axis(0)
            .radius(76.0)
            .width(1.0)
            .fill(null as Fill?)
        xAxis.scale()
            .minimum(0.0)
            .maximum(76.0)
        xAxis.ticks("{ interval: 1 }")
            .minorTicks("{ interval: 1 }")
        xAxis.labels().enabled(false)
        xAxis.ticks().enabled(false)
        xAxis.minorTicks().enabled(false)

        circularGauge.label(2.0)
            .text("${getTitle()}, <span style=\"\">puntaje $count</span>")
            .useHtml(true)
            .vAlign(VAlign.MIDDLE)
        circularGauge.label(2.0)
            .anchor(Anchor.RIGHT_CENTER)
            .padding(0.0, 10.0, 0.0, 0.0)
            .height((17.0 / 2.0).toString() + "%")
            .offsetY(60.0.toString() + "%")
            .offsetX(0.0)
        val bar2: Bar = circularGauge.bar(2.0)
        bar2.dataIndex(2.0)
        bar2.radius(60.0)
        bar2.width(17.0)


        bar2.fill(SolidFill(getColor(), 1.0))
        bar2.stroke(null)
        bar2.zIndex(5.0)
        val bar102: Bar = circularGauge.bar(102.0)
        bar102.dataIndex(5.0)
        bar102.radius(60.0)
        bar102.width(17.0)
        bar102.fill(SolidFill("#F5F4F4", 1.0))
        bar102.stroke("1 #e5e4e4")
        bar102.zIndex(4.0)


        circularGauge.margin(50.0, 50.0, 50.0, 50.0)

        anyChart.apply {
            setChart(circularGauge)
        }
    }

    private fun getColor(): String = when {
        count <= 22 -> colors[0]
        count <= 32 -> colors[1]
        count <= 48 -> colors[2]
        count <= 60 -> colors[3]
        else -> colors[4]
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
        else -> "Buscar ayuda"
    }

}