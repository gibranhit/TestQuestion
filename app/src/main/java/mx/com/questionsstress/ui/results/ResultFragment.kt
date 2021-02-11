package mx.com.questionsstress.ui.results

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.result_fragment.*
import mx.com.questionsstress.R

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bar = BarDataSet(listOf(BarEntry(20f, 66f)), "Hola").apply {
            valueTextColor = Color.BLACK
            valueTextSize = 18f
        }

        barChart.apply {
            setFitBars(true)
            data = BarData(bar)
            description.text = "Ejemplo"
        }



    }

}