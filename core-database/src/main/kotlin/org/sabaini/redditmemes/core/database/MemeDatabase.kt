package org.sabaini.redditmemes.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sabaini.redditmemes.core.database.entity.MemeEntity

@Database(
    entities = [MemeEntity::class],
    version = 15,
    exportSchema = true
)
abstract class MemeDatabase : RoomDatabase() {

    abstract fun memeDao(): MemeDao
}
