package org.sabaini.redditmemes.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.moodtracker.MainCoroutineRule
import org.sabaini.redditmemes.Util
import org.sabaini.redditmemes.entities.Meme

class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailViewModel
    private lateinit var memes: List<Meme>

    @Before
    fun setup() {
        memes = Util.getListOfMemes(1)
        viewModel = DetailViewModel()
    }

    @Test
    fun testSelectedMeme() {
        viewModel.setSelectedMeme(memes[0])
        assertThat(viewModel.selectedMeme.getOrAwaitValueTest()).isEqualTo(memes[0])
    }
}