package org.sabaini.redditmemes.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sabaini.redditmemes.db.getDatabase
import org.sabaini.redditmemes.model.Meme
import org.sabaini.redditmemes.repository.MemesRepository

class FeedViewModel(application: Application) : ViewModel(){

    private val database = getDatabase(application)
    private val memesRepository = MemesRepository(database)

    private val _navigateToSelectedMeme = MutableLiveData<Meme>()

    val navigateToSelectedMeme: LiveData<Meme>
        get() = _navigateToSelectedMeme

    init {
        viewModelScope.launch {
            memesRepository.refreshMemes("memes/hot.json")
        }
    }

    val memes = memesRepository.memes


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