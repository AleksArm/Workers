package com.highestaim.workers.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class WorkersModel (

    @Json(name = "data")
    val data : List<Data>? = null
) {
    @JsonClass(generateAdapter = true)
     data class Data(
        @Json(name = "id")
        val id: String? = null,

        @Json(name = "employee_name")
        val name: String? = null,

        @Json(name = "employee_salary")
        val salary: String? = null,

        @Json(name = "employee_age")
        val age: String? = null
    ) : Serializable
}