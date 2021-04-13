package mx.com.questionsstress.utils

import androidx.fragment.app.Fragment
import mx.com.questionsstress.R
import mx.com.questionsstress.domain.models.response.QuestionResponse
import mx.com.questionsstress.ui.model.Answer
import mx.com.questionsstress.ui.model.PainLevel
import mx.com.questionsstress.ui.model.ResultTest
import mx.com.questionsstress.ui.model.Test
import mx.com.questionsstress.ui.teststress.QuestionFragment

object Helper {

    fun getListTest()
            = listOf(Test("Test de estrés laboral", R.drawable.ic_suitcase),
        Test("Test de estrés escolar", R.drawable.ic_school),
        Test("Test de estrés por covid",R.drawable.ic_coronavirus)).toMutableList()

    fun getPainLevelList()
            = listOf(PainLevel( R.drawable.ic_5, 2),
        PainLevel( R.drawable.ic_4, 3),
        PainLevel( R.drawable.ic_3, 4),
        PainLevel( R.drawable.ic_2, 5),
        PainLevel( R.drawable.ic_1, 6)).toMutableList()

    fun getListQuestionStress()
            = mutableListOf(
        Answer(R.drawable.ic_sleep, "Imposibilidad de conciliar el sueño. "),
        Answer(R.drawable.ic_doctor, "Jaquecas y dolores de cabeza. "),
        Answer(R.drawable.ic_cookie, "Indigestiones o molestias gastrointestinales."),
        Answer(R.drawable.ic_tristesa, "Sensación de cansancio extremo o agotamiento."),
        Answer(R.drawable.ic_hamburger, "Tendencia de comer, beber o fumar más de lo habitual"),
        Answer(R.drawable.ic_love, "Disminución del interés sexual."),
        Answer(R.drawable.ic_doctors, "Respiración entrecortada o sensación de ahogo. "),
        Answer(R.drawable.ic_apetito, "Disminución del apetito."),
        Answer(R.drawable.ic_mindfulness, "Temblores musculares (por ejemplo tics nerviosos o parpadeos)."),
        Answer(R.drawable.ic_doctors, "Pinchazos o sensaciones dolorosas en distintas partes del cuerpo."),
        Answer(R.drawable.ic_joyride, "Tentaciones fuertes de no levantarse por la mañana."),
        Answer(R.drawable.ic_doctors, "Tendencias a sudar o palpitaciones. "))

    fun getFragments(list: MutableList<QuestionResponse>): MutableList<Fragment>
        = list.map { QuestionFragment.newInstance(it) }.toMutableList()

    fun getSearchList()
            = listOf(ResultTest( "Test de estrés laboral",40 , R.drawable.ic_3),
            ResultTest( "Test de estrés laboral",50 , R.drawable.ic_2),
            ResultTest( "Test de estrés laboral",61 , R.drawable.ic_1)).toMutableList()

}