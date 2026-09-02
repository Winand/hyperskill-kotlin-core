/**
 * MockK https://hyperskill.org/learn/step/27614
 */
package org.winand

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
//import io.mockk.mockk
//import io.mockk.spyk
import io.mockk.unmockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)  // required for @MockK, @InjectMockKs, @Spy
class WeatherServiceTest {
    @MockK  // Create a mock instance of ApiClient
    private lateinit var mockApiClient: ApiClient
//    private val mockApiClient = mockk<ApiClient>()

    @InjectMockKs  // Automatically inject the mock into the *real* WeatherService
    private lateinit var weatherService: WeatherService
//    private val weatherService = WeatherService(mockApiClient)

    @SpyK  // Create a spy of the real instance using spyk
    @InjectMockKs
    private lateinit var weatherServiceSpy: WeatherService

    @Test
    fun `test getWeather returns correct weather data`() {
        // Given: Set up the mock behavior and test data
        val city = "New York"
        val expectedWeather = Weather("Sunny", 25)
        // Define how mock should behave
        every { mockApiClient.fetchWeather(city, any()) } returns expectedWeather

        // When: Call the method under test
        val actualWeather = weatherService.getWeather(city)

        // Then: Verify the result
        assertEquals(expectedWeather, actualWeather)

        // Verify: Ensure that the interactions with the mock were as expected
        verify { mockApiClient.fetchWeather(city, any()) }
    }

    @Test
    fun `test getWeatherLocally using spy`() {
        // Create a spy of the real instance using spyk
        //val weatherServiceSpy = spyk(weatherService)
        // When: Call the method
        val weather = weatherServiceSpy.getWeatherLocally("Tokio")
        // Then: Verify that the method was called with the correct argument
        verify { weatherServiceSpy.getWeatherLocally("Tokio") }
        // Assert the result is as expected
        assertEquals("Cloudy in Tokio", weather)
    }

    @Test
    fun `test getWeatherLocally using mockkObject`() {
        // Give: Mock the WeatherService *companion* object
        mockkObject(WeatherService)
        // Define the behavior for the fetchWeatherLocally method
        every { WeatherService.getWeatherLocally("New York") } returns "Static in New York"
        // When: Call the fetchWeatherLocally method
        val weather = weatherService.getWeatherLocally("New York")
        // Then Verify that the fetchWeatherLocally method was called with the correct argument
        verify { WeatherService.getWeatherLocally("New York") }
        // Assert the result that the method returns the expected string
        assertEquals("Static in New York", weather)
        // Finally: Clear the mock after the test to avoid interference with other tests
        // unmockkObject(WeatherService)  // MockKExtension calls unmockAll after each test when lifecycle is set to the default PER_METHOD
    }
}