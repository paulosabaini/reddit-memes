package org.sabaini.redditmemes.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sabaini.redditmemes.data.local.MemeDao
import org.sabaini.redditmemes.data.local.MemeDatabase
import org.sabaini.redditmemes.data.remote.RedditApi
import org.sabaini.redditmemes.repositories.RedditMemesRepository
import org.sabaini.redditmemes.utilities.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRedditMemesRepository(dao: MemeDao, api: RedditApi): RedditMemesRepository {
        return RedditMemesRepository(dao, api)
    }

    @Singleton
    @Provides
    fun provideMemeDatabase(@ApplicationContext context: Context): MemeDatabase {

        val MIGRATION_14_15 = object : Migration(14, 15) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE DatabaseMeme ADD COLUMN name TEXT NOT NULL DEFAULT ''")
            }
        }

        return Room.databaseBuilder(
            context,
            MemeDatabase::class.java,
            "meme_db"
        )
            .addMigrations(MIGRATION_14_15)
            .build()
    }

    @Provides
    fun provideMemeDao(database: MemeDatabase): MemeDao {
        return database.memeDao()
    }

    @Singleton
    @Provides
    fun provideRedditApi(): RedditApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RedditApi::class.java)
    }
}