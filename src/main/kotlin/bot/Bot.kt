package ru.dfhub.test.bot

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.generics.TelegramClient
import ru.dfhub.test.token

/**
 * Основной класс бота
 */
class Bot : LongPollingSingleThreadUpdateConsumer {
    companion object {
        val client: TelegramClient = OkHttpTelegramClient(token)
    }

    /**
     * Обработчик бота *(aka. метод update)*
     * @param update Параметр, содержащий всю информацию, полученную(или нет) ботом
     */
    override fun consume(update: Update?) {
        if (update!!.hasMessage() && update.message.hasText()) {
            when (update.message.text) {
                "/start" -> {
                    Commands.start(update.message.chatId)
                }
                "/weather" -> {
                    Commands.weather(update.message.chatId)
                }
            }
        }
    }
}