package mx.com.questionsstress.domain.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultRequest(@Json(name = "typeTest")
                         val typeTest: Int = 0,
                         @Json(name = "title")
                         val title: String = "",
                         @Json(name = "correo")
                         val correo: String = "",
                         @Json(name = "maxScore")
                         val maxScore: Int = 0,
                         @Json(name = "score")
                         val score: Int = 0,
                         @Json(name = "date")
                         val date: String = "")