package org.sabaini.redditmemes.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.sabaini.moodtracker.MainCoroutineRule
import org.sabaini.redditmemes.Util.memes

class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel()
    }

    @Test
    fun testSelectedMeme() {
        viewModel.setSelectedMeme(memes[0])
        assertThat(viewModel.selectedMeme.getOrAwaitValueTest()).isEqualTo(memes[0])
    }
}