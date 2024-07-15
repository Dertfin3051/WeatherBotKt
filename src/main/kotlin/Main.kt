package ru.dfhub.test

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication
import ru.dfhub.test.bot.Bot
import java.util.*

val token: String = Config.getConfig().getString("bot-token")
val translations = ResourceBundle.getBundle("default", Locale.forLanguageTag(Config.getConfig().getString("lang"))!!)!!

fun main() {
    val bot = TelegramBotsLongPollingApplication()
    bot.registerBot(token, Bot())
}
