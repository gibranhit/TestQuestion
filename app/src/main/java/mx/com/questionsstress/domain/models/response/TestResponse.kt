package mx.com.questionsstress.domain.models.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class TestResponse(@Json(name = "_id")
                        val id: String = "",
                        @Json(name = "maxScore")
                        val maxScore: Int = 0,
                        @Json(name = "title")
                        val title: String = "",
                        @Json(name = "type")
                        val type: Int = 0,
                        @Json(name = "questions")
                        val questions : MutableList<QuestionResponse>):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class QuestionResponse(@Json(name = "title")
                            val title: String = "",
                            @Json(name = "image")
                            val image: String = ""):Parcelable