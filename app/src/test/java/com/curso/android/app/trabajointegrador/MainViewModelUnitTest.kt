package com.curso.android.app.trabajointegrador

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.curso.android.app.trabajointegrador.view.MainViewModel
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        val value = viewModel.comparator.value?.resultado
        assertEquals("", value)
    }
    @Test
    fun mainViewModel_CheckCompareTexts() = runTest {
        launch {
            viewModel.compareTexts("Este es un texto", "Este es un texto")
        }
        advanceUntilIdle()

        val value = viewModel.comparator.value?.resultado
        assertEquals("Los textos son iguales", value)
    }

    @Test
    fun mainViewModel_CheckResult() = runTest {
        launch {
            viewModel.compareTexts("Este es un texto", "Este es un texto diferente")
        }
        advanceUntilIdle()

        val value = viewModel.comparator.value?.resultado
        assertEquals("Los textos son diferentes", value)
    }
}