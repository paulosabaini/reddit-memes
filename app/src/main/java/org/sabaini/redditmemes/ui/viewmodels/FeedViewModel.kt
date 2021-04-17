package org.sabaini.redditmemes.ui.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sabaini.redditmemes.entities.Meme
import org.sabaini.redditmemes.repositories.RedditMemesRepository
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(repository: RedditMemesRepository) :
    ViewModel() {

    private var _memes = MutableLiveData<List<Meme>>()

    val memes: LiveData<List<Meme>>
        get() = _memes

    /* Store the meme clicked to make the navigation to detail screen */
    private val _navigateToSelectedMeme = MutableLiveData<Meme?>()

    val navigateToSelectedMeme: LiveData<Meme?>
        get() = _navigateToSelectedMeme

    init {
        _memes = repository.getMemes().asLiveData() as MutableLiveData<List<Meme>>
    }

    /* Auxiliary functions */

    fun displayMemeDetail(meme: Meme) {
        _navigateToSelectedMeme.value = meme
    }

    fun displayMemeComplete() {
        _navigateToSelectedMeme.value = null
    }
}