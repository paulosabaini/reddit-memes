package org.sabaini.redditmemes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sabaini.redditmemes.db.getDatabase
import org.sabaini.redditmemes.model.Meme
import org.sabaini.redditmemes.repository.MemesRepository

class FeedViewModel(application: Application) : ViewModel() {

    /* Database and Repository variables */
    private val database = getDatabase(application)
    private val memesRepository = MemesRepository(database)

    /* Store the meme clicked to make the navigation to detail screen */
    private val _navigateToSelectedMeme = MutableLiveData<Meme>()

    val navigateToSelectedMeme: LiveData<Meme>
        get() = _navigateToSelectedMeme

    /* List of filters used to create the Chips */
    private val _filters = MutableLiveData<List<MemesFilter>>()

    val filters: LiveData<List<MemesFilter>>
        get() = _filters

    /* Holds the current selected filter */
    private val _selectedFilter = MutableLiveData<MemesFilter>()

    val selectedFilter: LiveData<MemesFilter>
        get() = _selectedFilter

    init {
        /* Converts the a enum class into a list */
        _filters.value = enumValues<MemesFilter>().asList()

        /* Always start the application with the hot filter selected */
        _selectedFilter.value = MemesFilter.HOT
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

    fun setSelectedFilter(filter: MemesFilter) {
        _selectedFilter.value = filter
    }

    /* Make a request to the API to get new memes and update the memes displayed */
    fun refresh() {
        viewModelScope.launch {
            memesRepository.refreshMemes("memes/" + _selectedFilter.value!!.value + ".json", _selectedFilter.value!!.value)
        }
    }

    /* Enum class containing the filters */
    enum class MemesFilter(val value: String) {
        HOT("hot"),
        NEW("new"),
        TOP("top"),
        RISING("rising"),
        CONTROVERSIAL("controversial")
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