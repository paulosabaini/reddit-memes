package org.sabaini.redditmemes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/* Data access operations in the database */

@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg memes: DatabaseMeme)

    @Query("select * from databasememe where id in (select max(id) from databasememe group by position) order by position")
    fun load(): Flow<List<DatabaseMeme>>
}