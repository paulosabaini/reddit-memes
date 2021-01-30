package org.sabaini.redditmemes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sabaini.redditmemes.api.Network
import org.sabaini.redditmemes.api.asDatabaseModel
import org.sabaini.redditmemes.db.MemesDb
import org.sabaini.redditmemes.db.asDomainModel
import org.sabaini.redditmemes.model.Meme
import org.sabaini.redditmemes.viewmodel.FeedViewModel
import java.lang.Exception

/* Repository that provides data to the ViewModel */

class MemesRepository(private val database: MemesDb) {

    var filter = "hot"

    var memes: LiveData<List<Meme>> = Transformations.map(database.memeDao.getMemes(filter)) {
        it.asDomainModel()
    }

    /* Makes a request to the API and then insert the result in the database */
    suspend fun refreshMemes(url: String, filter: String) {
        withContext(Dispatchers.IO) {
            try {
                val memes = Network.retrofitService.getMemes(url)
                database.memeDao.insertAll(*memes.asDatabaseModel(filter))
            } catch (e: Exception) {
                Log.d("MemesRepository", e.toString())
            }
        }
    }
}