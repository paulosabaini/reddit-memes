package org.sabaini.redditmemes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* Provides a instance of the database */

@Database(entities = [DatabaseMeme::class], version = 12)
abstract class MemesDb : RoomDatabase() {
    abstract val memeDao: MemeDao
}

private lateinit var INSTANCE: MemesDb

fun getDatabase(context: Context): MemesDb {
    synchronized(MemesDb::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    MemesDb::class.java,
                    "memes")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
    return INSTANCE
}