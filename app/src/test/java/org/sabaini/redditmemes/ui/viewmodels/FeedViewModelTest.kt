package org.sabaini.redditmemes.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flow
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
import org.sabaini.redditmemes.Util.memes
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

    @Before
    fun setup() = runBlocking {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getMemes()).thenAnswer {
            return@thenAnswer flow {
                emit(memes)
            }
        }
        feedViewModel = FeedViewModel(repository)

    }

    @Test
    fun testViewModelInit() {
        assertThat(feedViewModel.memes.getOrAwaitValueTest()).isEqualTo(memes)
    }

    @Test
    fun testDisplayMemeDetail() {
        feedViewModel.displayMemeDetail(memes[0])
        assertThat(feedViewModel.navigateToSelectedMeme.getOrAwaitValueTest()).isEqualTo(memes[0])
    }

    @Test
    fun testDisplayMemeComplete() {
        feedViewModel.displayMemeDetail(memes[0])
        feedViewModel.displayMemeComplete()
        assertThat(feedViewModel.navigateToSelectedMeme.getOrAwaitValueTest()).isNull()
    }

}