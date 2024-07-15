package ru.dfhub.test.bot

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.dfhub.test.Config
import ru.dfhub.test.api.WeatherApi
import ru.dfhub.test.api.WeatherData
import ru.dfhub.test.translations

/**
 * Класс, который несёт основной функционал бота
 * Содержит комадны бота, каждая из которых находится в отдельном методе
 */
object Commands {
    fun start(chatId: Long) {
        val sendMessage: SendMessage = SendMessage.builder()
            .chatId(chatId)
            .text(translations.getString("weatherData.start"))
            .parseMode("Markdown")
            .build()
        Bot.client.execute(sendMessage)
    }

    fun weather(chatId: Long) {
        val sendMessage: SendMessage = SendMessage.builder()
            .chatId(chatId)
            .text(getWeatherDescription())
            .parseMode("Markdown")
            .build()
        Bot.client.execute(sendMessage)
    }

    private fun getWeatherDescription() : String {
        var config = Config.getConfig()
        var weatherAPI = WeatherApi(
            config.getJSONObject("location").getDouble("lat"),
            config.getJSONObject("location").getDouble("lon"),
            config.getString("api-key"),
            config.getString("lang")
        )
        return WeatherData(weatherAPI.getWeatherData()).getInfo()
    }
}
