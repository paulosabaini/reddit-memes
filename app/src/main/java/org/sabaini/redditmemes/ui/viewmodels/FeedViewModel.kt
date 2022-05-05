package org.sabaini.redditmemes.ui.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sabaini.redditmemes.entities.Meme
import org.sabaini.redditmemes.repositories.RedditMemesRepository
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: RedditMemesRepository) :
    ViewModel() {

    val memes: StateFlow<List<Meme>> = repository.getMemes().stateIn(
        initialValue = listOf(),
        scope = viewModelScope,
        started = WhileSubscribed(5000),
    )

    /* Store the meme clicked to make the navigation to detail screen */
    private val _navigateToSelectedMeme = MutableLiveData<Meme?>()

    val navigateToSelectedMeme: LiveData<Meme?>
        get() = _navigateToSelectedMeme

    /* Auxiliary functions */

    fun displayMemeDetail(meme: Meme) {
        _navigateToSelectedMeme.value = meme
    }

    fun displayMemeComplete() {
        _navigateToSelectedMeme.value = null
    }

    fun loadMore() {
        viewModelScope.launch {
            val last = memes.value.last()
            repository.loadMoreMemes(last.name, last.position)
        }
    }
}