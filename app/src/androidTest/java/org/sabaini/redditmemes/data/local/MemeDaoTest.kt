package org.sabaini.redditmemes.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.sabaini.moodtracker.MainCoroutineRuleAndroidTest
import org.sabaini.redditmemes.UtilAndroidTest.memes
import org.sabaini.redditmemes.getOrAwaitValue

@RunWith(AndroidJUnit4::class)
class MemeDaoTest {

    private lateinit var database: MemeDatabase
    private lateinit var memeDao: MemeDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRuleAndroidTest()

    @Before
    fun setup() = runBlocking {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MemeDatabase::class.java,
        ).build()
        memeDao = database.memeDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSaveAndLoadMemes() = runBlockingTest {
        memeDao.save(*memes.toTypedArray())
        val result = memeDao.load().take(1).toList()[0]
        assertThat(result).isEqualTo(memes)
    }
}