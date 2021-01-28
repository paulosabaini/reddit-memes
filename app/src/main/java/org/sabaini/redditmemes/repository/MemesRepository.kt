package org.sabaini.redditmemes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sabaini.redditmemes.api.Network
import org.sabaini.redditmemes.api.asDatabaseModel
import org.sabaini.redditmemes.db.MemesDb
import org.sabaini.redditmemes.db.asDomainModel
import org.sabaini.redditmemes.model.Meme

class MemesRepository(private val database: MemesDb) {

    val memes: LiveData<List<Meme>> = Transformations.map(database.memeDao.getMemes()) {
        it.asDomainModel()
    }

    suspend fun refreshMemes(url: String) {
        withContext(Dispatchers.IO) {
            val memes = Network.retrofitService.getMemes(url)
            database.memeDao.insertAll(*memes.asDatabaseModel())
        }
    }
}