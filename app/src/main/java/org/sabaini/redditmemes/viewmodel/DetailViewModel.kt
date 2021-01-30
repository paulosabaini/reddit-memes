package org.sabaini.redditmemes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sabaini.redditmemes.model.Meme

class DetailViewModel : ViewModel() {
    private val _selectedMeme = MutableLiveData<Meme>()

    val selectedMeme: LiveData<Meme>
        get() = _selectedMeme

    fun setSelectedMeme(meme: Meme) {
        _selectedMeme.value = meme
    }

}