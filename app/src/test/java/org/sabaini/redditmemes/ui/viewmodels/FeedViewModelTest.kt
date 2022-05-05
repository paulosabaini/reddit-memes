package org.sabaini.redditmemes.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.sabaini.moodtracker.MainCoroutineRule
import org.sabaini.redditmemes.Util.getListOfMemes
import org.sabaini.redditmemes.entities.Meme
import org.sabaini.redditmemes.repositories.RedditMemesRepository

@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: RedditMemesRepository

    private lateinit var feedViewModel: FeedViewModel

    private lateinit var memes: List<Meme>

    @Before
    fun setup() = runBlocking {
        memes = getListOfMemes(24)
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getMemes()).thenAnswer {
            return@thenAnswer flow {
                emit(memes)
            }
        }
        feedViewModel = FeedViewModel(repository)
    }

    @After
    fun teardown() {

    }

    @Test
    @ExperimentalCoroutinesApi
    fun testViewModelInit() = runTest {
        assertThat(feedViewModel.memes.drop(0).first()).isEqualTo(memes)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testDisplayMemeDetail() = runTest {
        feedViewModel.displayMemeDetail(memes[0])
        assertThat(feedViewModel.navigateToSelectedMeme.getOrAwaitValueTest()).isEqualTo(memes[0])
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testDisplayMemeComplete() = runTest {
        feedViewModel.displayMemeDetail(memes[0])
        feedViewModel.displayMemeComplete()
        assertThat(feedViewModel.navigateToSelectedMeme.getOrAwaitValueTest()).isNull()
    }
}