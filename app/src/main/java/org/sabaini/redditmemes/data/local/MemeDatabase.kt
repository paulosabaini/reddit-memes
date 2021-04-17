package org.sabaini.redditmemes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/* Provides a instance of the database */

@Database(entities = [DatabaseMeme::class], version = 14)
abstract class MemeDatabase : RoomDatabase() {

    abstract fun memeDao(): MemeDao
}