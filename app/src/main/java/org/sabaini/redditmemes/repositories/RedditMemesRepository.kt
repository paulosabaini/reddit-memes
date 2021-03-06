package org.sabaini.redditmemes.repositories

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sabaini.redditmemes.data.local.MemeDao
import org.sabaini.redditmemes.data.local.asEntitieMeme
import org.sabaini.redditmemes.data.remote.RedditApi
import org.sabaini.redditmemes.data.remote.asDatabaseMeme
import org.sabaini.redditmemes.entities.Meme
import java.lang.Exception
import javax.inject.Inject

/* Repository that provides data to the ViewModel */

class RedditMemesRepository @Inject constructor(
    private val memeDao: MemeDao,
    private val redditApi: RedditApi
) {

    fun getMemes(): Flow<List<Meme>> {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            refreshMemes()
            deleteOldCache()
        }

        return memeDao.load().map { list ->
            list.asEntitieMeme().filter { !it.stickied && !it.isVideo }
        }
    }

    /* Makes a request to the API and then insert the result in the database */
    private suspend fun refreshMemes() {
        withContext(Dispatchers.IO) {
            try {
                val memes = redditApi.getMemes()
                memeDao.save(*memes.asDatabaseMeme(null))
            } catch (e: Exception) {
                Log.d("MemesRepository", e.stackTraceToString())
            }
        }
    }

    suspend fun loadMoreMemes(name: String, lastPosition: Int) {
        withContext(Dispatchers.IO) {
            try {
                val memes = redditApi.getMemesAfter(name)
                memeDao.save(*memes.asDatabaseMeme(lastPosition))
            } catch (e: Exception) {
                Log.d("MemesRepository", e.stackTraceToString())
            }
        }
    }

    private suspend fun deleteOldCache() {
        withContext(Dispatchers.IO) {
            try {
                memeDao.delete()
            } catch (e: Exception) {
                Log.d("MemesRepository", e.stackTraceToString())
            }
        }
    }
}