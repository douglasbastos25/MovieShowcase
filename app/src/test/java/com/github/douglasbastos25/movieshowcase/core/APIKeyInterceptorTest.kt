package com.github.douglasbastos25.movieshowcase.core

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import org.junit.Test

class APIKeyInterceptorTest {

    @Test
    fun addsAPIKeyToRequestUrl() {

        val expectedUrl = "https://api.themoviedb.org/3/movie/38?api_key=KEY"

        val slot = slot<Request>()

        val previousRequest =
            Request.Builder().get().url("https://api.themoviedb.org/3/movie/38").build()

        val previousChainInterceptor = mockk<Interceptor.Chain>()
        every {
            previousChainInterceptor.request()
        } returns previousRequest

        every {
            previousChainInterceptor.proceed(request = capture(slot))
        } answers {
            Response.Builder()
                .protocol(Protocol.HTTP_1_0)
                .message("OK")
                .code(200)
                .request(slot.captured)
                .build()
        }

        val apiKeyInterceptor = APIKeyInterceptor("KEY")

        val response = apiKeyInterceptor.intercept(previousChainInterceptor)

        assertEquals(expectedUrl, response.request.url.toString())

    }
}
