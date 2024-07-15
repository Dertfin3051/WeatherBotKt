package ru.dfhub.test.bot

import org.json.JSONObject
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.dfhub.test.Config
import ru.dfhub.test.api.WeatherApi
import ru.dfhub.test.api.WeatherData

/**
 * Класс, который несёт основной функционал бота
 * Содержит комадны бота, каждая из которых находится в отдельном методе
 */
class Commands {

    fun start(chatId: Long) {
        val sendMessage: SendMessage = SendMessage.builder()
            .chatId(chatId)
            .text("**Добро пожаловать!**\n\nВ данном боте вы можете узнать погоду в городе Можайск. Для того, чтобы начать, введите команду /weather")
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