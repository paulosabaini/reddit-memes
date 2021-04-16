package org.sabaini.redditmemes.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sabaini.redditmemes.data.local.getDatabase
import org.sabaini.redditmemes.entities.Meme
import org.sabaini.redditmemes.repositories.MemesRepository

class FeedViewModel(application: Application) : ViewModel() {

    /* Database and Repository variables */
    private val database = getDatabase(application)
    private val memesRepository = MemesRepository(database)

    /* Store the meme clicked to make the navigation to detail screen */
    private val _navigateToSelectedMeme = MutableLiveData<Meme>()

    val navigateToSelectedMeme: LiveData<Meme>
        get() = _navigateToSelectedMeme

    init {
        viewModelScope.launch {
            memesRepository.refreshMemes()
        }
    }

    /* Holds the list of memes displayed in the screen */
    val memes = memesRepository.memes

    /* Auxiliary functions */

    fun displayMemeDetail(meme: Meme) {
        _navigateToSelectedMeme.value = meme
    }

    fun displayMemeComplete() {
        _navigateToSelectedMeme.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FeedViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}