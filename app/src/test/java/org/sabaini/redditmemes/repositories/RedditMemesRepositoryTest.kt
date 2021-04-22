package org.sabaini.redditmemes.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.sabaini.moodtracker.MainCoroutineRule
import org.sabaini.redditmemes.Util
import org.sabaini.redditmemes.Util.dbMemes
import org.sabaini.redditmemes.Util.response
import org.sabaini.redditmemes.data.local.DatabaseMeme
import org.sabaini.redditmemes.data.local.MemeDao
import org.sabaini.redditmemes.data.remote.RedditApi

@RunWith(MockitoJUnitRunner::class)
class RedditMemesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var memeDao: MemeDao

    @Mock
    private lateinit var redditApi: RedditApi
    private lateinit var repository: RedditMemesRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = RedditMemesRepository(memeDao, redditApi)
    }

    @Test
    fun testGetMemes() = runBlocking {
        Mockito.`when`(memeDao.load()).thenAnswer {
            return@thenAnswer flow {
                emit(dbMemes)
            }
        }

        Mockito.`when`(redditApi.getMemes()).thenAnswer {
            return@thenAnswer response
        }

        val m = repository.getMemes()
        assertThat(m.take(1).toList()[0].size).isEqualTo(dbMemes.size)
    }
}