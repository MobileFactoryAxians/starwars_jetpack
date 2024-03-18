package com.axians.dre_app.utils.json

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//https://www.javaguides.net/2018/10/gson-custom-serialization-and-deseriliazation-examples.html
internal class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    private val formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            //.withLocale(Locale.ENGLISH)

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDateTime {
        return LocalDateTime.parse(
            json.asString,
            formatter
        )
    }
}