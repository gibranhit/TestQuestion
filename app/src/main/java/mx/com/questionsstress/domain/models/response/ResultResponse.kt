package mx.com.questionsstress.domain.models.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResultResponse(@Json(name = "_id")
                          val id: String = "",
                          @Json(name = "typeTest")
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
                          val date: String = ""): Parcelable
