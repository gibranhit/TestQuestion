package mx.com.questionsstress.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Test(var name: String,
                var background: Int): Parcelable


@Parcelize
data class QuestionTest(var answer: Int,
                        var level: Int): Parcelable

@Parcelize
data class PainLevel(var image: Int,
                     var level: Int): Parcelable