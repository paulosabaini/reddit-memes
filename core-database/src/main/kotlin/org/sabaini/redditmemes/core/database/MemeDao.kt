package org.sabaini.redditmemes.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.sabaini.redditmemes.core.database.entity.MemeEntity

@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg memes: MemeEntity)

    @Query("select * from memeentity where id in (select max(id) from memeentity group by position) order by position")
    fun load(): Flow<List<MemeEntity>>

    @Query("delete from memeentity where position > 24")
    fun delete()
}