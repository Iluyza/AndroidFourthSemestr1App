package com.example.fourthsemestr1.domain.usecase

import com.example.fourthsemestr1.domain.entity.Weather
import com.example.fourthsemestr1.domain.repository.WeatherRepository
import com.example.fourthsemestr1.utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
internal class GetWeatherListUseCaseTest {
    @MockK
    lateinit var repository: WeatherRepository

    @get:Rule
    val coroutineRule: MainCoroutineRule = MainCoroutineRule()

    private lateinit var useCase: GetWeatherListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = spyk(GetWeatherListUseCase(repository, coroutineRule.testDispatcher))
    }

    @Test
    @DisplayName("Successful getting weather list")
    operator fun invoke() = runBlocking {
        val expectedList = arrayListOf<Weather>(
            mockk { every { id } returns 1 },
            mockk { every { id } returns 2 },
        )

        coEvery { repository.getWeatherList(any(), any(), any()) } returns expectedList

        val result = useCase(1.0, 1.0, 5)

        assertEquals(expectedList, result)
    }
}