/**
 * MockK https://hyperskill.org/learn/step/27614
 */
package org.winand

data class Weather(val condition: String, val temperature: Int)

/**
 * Interface for the API client that the WeatherService depends on.
 */
interface ApiClient {
    fun fetchWeather(city: String, arg2: Int): Weather
}

/**
 * WeatherService uses the ApiClient to fetch weather data for a given city.
 */
class WeatherService(private val apiClient: ApiClient) {
    fun getWeather(city: String): Weather {
        return apiClient.fetchWeather(city, 0)
    }

    fun getWeatherLocally(city: String): String =
        WeatherService.getWeatherLocally(city)

    companion object {
        fun getWeatherLocally(city: String): String =
            "Cloudy in $city"
    }
}

fun main() {
    println(WeatherService(object : ApiClient {
        override fun fetchWeather(city: String, arg2: Int): Weather =
            Weather("Rainy in $city", 23)
    }).getWeatherLocally("York"))

    println(WeatherService.getWeatherLocally("York"))


    val strings = readlnOrNull()?.split(" ")
    print("|${strings?.get(0)}|")
    println(strings.isNullOrEmpty())
}