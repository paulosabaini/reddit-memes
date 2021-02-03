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

    @Query("select * from databasememe where id in (select max(id) from databasememe group by position) order by position")
    fun getMemes(): LiveData<List<DatabaseMeme>>
}