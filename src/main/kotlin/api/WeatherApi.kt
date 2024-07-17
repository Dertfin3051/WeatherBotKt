package ru.dfhub.test.api

import org.json.JSONObject
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * Класс, который хранит ссылку конструктор ссылки на API, а также метод для получения его результатов
 * @param lat Географическая ширина
 * @param lon Географическая высота
 * @param apiKey API-Ключ для OpenWeather
 * @param lang Язык *(ru/en)*. Хз, зачем он тут, но пусть будет
 */
open class WeatherApi(lat: Double, lon: Double, apiKey: String, lang: String) {
    private var url: String = "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$apiKey&lang=$lang&units=metric"

    fun getWeatherData(): JSONObject {
        val httpClient: HttpClient = HttpClient.newHttpClient();
        val httpRequest: HttpRequest = HttpRequest.newBuilder(URI.create(url)).build();
        val responseStream: HttpResponse<Stream<String>>? = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofLines())
        val response: String = responseStream!!.body().collect(Collectors.joining());

        return JSONObject(response)
    }
}