package ru.dfhub.test.api

import org.json.JSONObject

/**
 * Класс, который содержит себе информацию о погоде в удобном виде
 * @param data Результат WeatherApi.getWeatherData();
 */
class WeatherData(data: JSONObject) {

    var summary: String
    var tempMax: Float
    var tempMin: Float
    var windSpeed: Float
    var clouds: Int

    private val info: String = """
        *Погода в Можайске*
            
        Сегодня *{summary}*, температура составляет от *{min}°С* до *{max}°С.*
        Скорость ветра: *{wind_speed} м/с*
        Облачность: *{clouds}%*
    """.trimIndent()

    init {
        summary = data.getJSONArray("weather").getJSONObject(0).getString("description")
        tempMax = data.getJSONObject("main").getFloat("temp_max")
        tempMin = data.getJSONObject("main").getFloat("temp_min")
        windSpeed = data.getJSONObject("wind").getFloat("speed")
        clouds = data.getJSONObject("clouds").getInt("all")
    }

    fun getInfo(): String {
        return info
            .replace("{summary}", summary)
            .replace("{min}", tempMin.toString())
            .replace("{max}", tempMax.toString())
            .replace("{wind_speed}", windSpeed.toString())
            .replace("{clouds}", clouds.toString())
    }

}