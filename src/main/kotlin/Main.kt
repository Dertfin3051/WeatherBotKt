package ru.dfhub.test

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication
import ru.dfhub.test.bot.Bot

val token: String = Config.getConfig().getString("bot-token")

fun main() {
    val bot: TelegramBotsLongPollingApplication = TelegramBotsLongPollingApplication()
    bot.registerBot(token, Bot())
}
