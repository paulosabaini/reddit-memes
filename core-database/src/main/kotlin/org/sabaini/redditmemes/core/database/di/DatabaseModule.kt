package org.sabaini.redditmemes.core.database.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.sabaini.redditmemes.core.database.MemeDao
import org.sabaini.redditmemes.core.database.MemeDatabase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): MemeDatabase {
        return Room.databaseBuilder(
            application,
            MemeDatabase::class.java,
            "meme_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMemeDao(database: MemeDatabase): MemeDao {
        return database.memeDao()
    }
}