package org.sabaini.redditmemes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sabaini.redditmemes.entities.Meme
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _selectedMeme = MutableLiveData<Meme>()

    val selectedMeme: LiveData<Meme>
        get() = _selectedMeme

    fun setSelectedMeme(meme: Meme) {
        _selectedMeme.value = meme
    }

}