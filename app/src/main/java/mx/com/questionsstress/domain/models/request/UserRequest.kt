package mx.com.questionsstress.domain.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRequest(@Json(name = "email")
                        val email: String = "",
                        @Json(name = "password")
                        val password: String = "")
