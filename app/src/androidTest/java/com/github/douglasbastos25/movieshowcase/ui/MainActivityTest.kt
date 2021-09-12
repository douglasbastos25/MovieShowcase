package com.github.douglasbastos25.movieshowcase.ui

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.github.douglasbastos25.movieshowcase.R
import com.github.douglasbastos25.movieshowcase.core.preferences.SharedPreferencesLike
import com.github.douglasbastos25.movieshowcase.core.preferences.SharedPreferencesLike.Companion.LIKE_VALUE
import com.github.douglasbastos25.movieshowcase.helpers.CustomTestRunner
import com.github.douglasbastos25.movieshowcase.test.BuildConfig.TMDB_API_KEY
import com.github.douglasbastos25.movieshowcase.ui.MainActivity.Companion.MOVIE_ID
import com.github.douglasbastos25.movieshowcase.ui.SimilarMoviesListAdapter.ViewHolder
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private val lock = CountDownLatch(4)

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val server = MockWebServer().also { it.start(CustomTestRunner.MOCK_WEB_SERVER_PORT) }

    @get :Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setup() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val response = when (request.path) {
                    ("/movie/$MOVIE_ID?api_key=$TMDB_API_KEY") -> MockResponse().setResponseCode(200)
                        .setBody(getFileContent("movie.json"))
                    ("/genre/movie/list?api_key=$TMDB_API_KEY") -> MockResponse().setResponseCode(
                        200
                    )
                        .setBody(getFileContent("genre.json"))
                    ("/configuration?api_key=$TMDB_API_KEY") -> MockResponse().setResponseCode(200)
                        .setBody(getFileContent("configuration.json"))
                    ("/movie/$MOVIE_ID/similar?api_key=$TMDB_API_KEY") -> MockResponse().setResponseCode(
                        200
                    )
                        .setBody(getFileContent("similar-movies.json"))
                    else -> MockResponse().setResponseCode(500)
                }
                lock.countDown()
                return response
            }
        }
    }

    @Test
    fun showMovieDataFromServer() {
        lock.await(2, TimeUnit.SECONDS)

        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("Eternal Sunshine of the Spotless Mind")))
        onView(withId(R.id.tv_likes))
            .check(matches(withText("110000")))
        onView(withId(R.id.tv_views))
            .check(matches(withText("38000.0")))

    }

    @Test
    fun checkLikeButtonWhenClicked() {
        lock.await(2, TimeUnit.SECONDS)
        ensureSharedPreferencesDoesNotExist()

        onView(withId(R.id.bt_like))
            .perform(click())
        onView(withId(R.id.bt_like))
            .check(matches(isSelected()))
    }

    @Test
    fun uncheckLikeButtonWhenClicked() {
        lock.await(2, TimeUnit.SECONDS)
        ensureSharedPreferencesExists()

        onView(withId(R.id.bt_like))
            .perform(click())
        onView(withId(R.id.bt_like))
            .check(matches(isNotSelected()))
    }

    @Test
    fun checkContentInRecyclerView() {
        lock.await(2, TimeUnit.SECONDS)
        Thread.sleep(500)

        onView(withId(R.id.rv_similar_movies))
            .perform(scrollTo<ViewHolder>(hasDescendant(withText("The World's End"))))
        onView(withId(R.id.rv_similar_movies))
            .perform(scrollTo<ViewHolder>(hasDescendant(withText("2013"))))
        onView(withId(R.id.rv_similar_movies))
            .perform(scrollTo<ViewHolder>(hasDescendant(withText("Adventure, Science Fiction, Comedy"))))
        onView(withId(R.id.rv_similar_movies))
            .perform(scrollTo<ViewHolder>(hasDescendant(withText("Shaun of the Dead"))))
        onView(withId(R.id.rv_similar_movies))
            .perform(scrollTo<ViewHolder>(hasDescendant(withText("2004"))))
        onView(withId(R.id.rv_similar_movies))
            .perform(scrollTo<ViewHolder>(hasDescendant(withText("Adventure, Comedy"))))

    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun getFileContent(fileName: String): String {
        val context = InstrumentationRegistry.getInstrumentation().context
        val content: String
        try {
            content = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return content
    }

    private fun ensureSharedPreferencesDoesNotExist() {
        context.getSharedPreferences(
            SharedPreferencesLike.PREFERENCE_FILE_KEY,
            Context.MODE_PRIVATE
        ).edit().remove(LIKE_VALUE).apply()
    }

    private fun ensureSharedPreferencesExists() {
        context.getSharedPreferences(
            SharedPreferencesLike.PREFERENCE_FILE_KEY,
            Context.MODE_PRIVATE
        ).edit().putBoolean(LIKE_VALUE, true).apply()
    }
}
