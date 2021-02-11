package mx.com.questionsstress.utils

import androidx.fragment.app.Fragment
import mx.com.questionsstress.R
import mx.com.questionsstress.ui.model.PainLevel
import mx.com.questionsstress.ui.model.Test
import mx.com.questionsstress.ui.teststress.QuestionFragment

object Helper {

    fun getListTest()
            = listOf(Test("Test de estres laboral", R.drawable.ic_suitcase),
        Test("Test de estres escolar", R.drawable.ic_school),
        Test("Test de estres por covid",R.drawable.ic_coronavirus)).toMutableList()

    fun getPainLevelList()
            = listOf(PainLevel( R.drawable.ic_5, 1),
        PainLevel( R.drawable.ic_4, 2),
        PainLevel( R.drawable.ic_3, 3),
        PainLevel( R.drawable.ic_2, 4),
        PainLevel( R.drawable.ic_1, 5)).toMutableList()

    fun getListQuestionStress()
            = listOf("Imposibilidad de conciliar el sueño. ",
        "Jaquecas y dolores de cabeza. ",
        "Indigestiones o molestias gastrointestinales.",
        "Sensación de cansancio extremo o agotamiento.",
        "Tendencia de comer, beber o fumar más de lo habitual",
        "Disminución del interés sexual.",
        "Respiración entrecortada o sensación de ahogo. ",
        "Disminución del apetito.",
        "Temblores musculares (por ejemplo tics nerviosos o parpadeos).",
        "Pinchazos o sensaciones dolorosas en distintas partes del cuerpo.",
        "Tentaciones fuertes de no levantarse por la mañana.",
        "Tendencias a sudar o palpitaciones. ")

    fun getFragments(list: List<String>): MutableList<Fragment>
        = list.map {
            QuestionFragment.newInstance(it)
        }.toMutableList()

}