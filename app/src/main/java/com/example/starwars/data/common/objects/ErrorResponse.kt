package com.example.starwars.data.common.objects

import com.google.gson.annotations.SerializedName

/*public class ErrorResponse(
    public val message: String?,
    public val error_description: String? = null,
    public val tokenExpired: Boolean? = null,
    public val errors: Map<String, ArrayList<String>>? = null
) {
}*/

data class ErrorResponse (

    @SerializedName("Errors") val errors : List<String>,
    @SerializedName("StatusCode") val statusCode : Int
)