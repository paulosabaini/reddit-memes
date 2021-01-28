package org.sabaini.redditmemes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemeDao {
    @Query("select * from databasememe order by score DESC")
    fun getMemes(): LiveData<List<DatabaseMeme>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg memes: DatabaseMeme)
}