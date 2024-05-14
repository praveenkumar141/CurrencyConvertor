import com.example.currencyconvertor.ui.theme.data.remote.CurrencyApi
import com.example.currencyconvertor.ui.theme.data.remote.CurrencyResponse
import com.example.currencyconvertor.ui.theme.data.repository.CurrencyRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import io.mockk.mockk


class CurrencyRepositoryImplTest {
    @Test
    fun getLatestSuccess() {
        val currencyApi = mockk<CurrencyApi>()

        val mockResponse = CurrencyResponse("", "", 0L, "USD", emptyMap())
        coEvery { currencyApi.getLatestRates() } returns mockResponse

        val currencyRepository = CurrencyRepositoryImpl(currencyApi)
        val result = runBlocking {
            currencyRepository.getLatestRates()
        }
        assertEquals("USD", result.base)
    }
}
