package org.sabaini.redditmemes.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sabaini.redditmemes.data.remote.Network
import org.sabaini.redditmemes.data.remote.asDatabaseModel
import org.sabaini.redditmemes.data.local.MemesDb
import org.sabaini.redditmemes.data.local.asDomainModel
import org.sabaini.redditmemes.models.Meme
import java.lang.Exception

/* Repository that provides data to the ViewModel */

class MemesRepository(private val database: MemesDb) {

    val memes: LiveData<List<Meme>> = Transformations.map(database.memeDao.getMemes()) {
        it.asDomainModel().filter { !it.stickied && !it.isVideo }
    }

    /* Makes a request to the API and then insert the result in the database */
    suspend fun refreshMemes() {
        withContext(Dispatchers.IO) {
            try {
                val memes = Network.retrofitService.getMemes()
                database.memeDao.insertAll(*memes.asDatabaseModel())
            } catch (e: Exception) {
                Log.d("MemesRepository", e.toString())
            }
        }
    }
}