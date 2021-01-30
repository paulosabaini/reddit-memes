package org.sabaini.redditmemes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/* Data access operations in the database */

@Dao
interface MemeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg memes: DatabaseMeme)

    @Query("select * from databasememe where databasememe.filter = :filter order by position, created DESC")
    fun getMemes(filter: String): LiveData<List<DatabaseMeme>>
}