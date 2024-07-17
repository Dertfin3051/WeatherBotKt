package ru.dfhub.test.api

import org.json.JSONObject
import ru.dfhub.test.translations

/**
 * Класс, который содержит себе информацию о погоде в удобном виде
 * @param data Результат WeatherApi.getWeatherData();
 */
class WeatherData(data: JSONObject) {
    var summary: String = data.getJSONArray("weather").getJSONObject(0).getString("description")
    var tempMax: Float = data.getJSONObject("main").getFloat("temp_max")
    var tempMin: Float = data.getJSONObject("main").getFloat("temp_min")
    var windSpeed: Float = data.getJSONObject("wind").getFloat("speed")
    var clouds: Int = data.getJSONObject("clouds").getInt("all")

    private val info: String = translations.getString("weatherData.info")

    fun getInfo(): String {
        return info
            .replace("{summary}", summary)
            .replace("{min}", tempMin.toString())
            .replace("{max}", tempMax.toString())
            .replace("{wind_speed}", windSpeed.toString())
            .replace("{clouds}", clouds.toString())
    }
}
