package org.sabaini.redditmemes.entities

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.sabaini.redditmemes.Util.memes
import java.time.Instant

class MemeTest {

    @Test
    fun testGetNumComments() {
        assertThat(memes[0].getNumComments()).isEqualTo("${memes[0].numComments} comments")
    }

    @Test
    fun testGetPoints() {
        assertThat(memes[0].getPoints()).isEqualTo("1,000 points")
    }

    @Test
    fun testSubtitleContainsAuthor() {
        assertThat(memes[0].subtitle()).contains(memes[0].author)
    }

    @Test
    fun testSubtitleContainsTimeElapsed() {
        assertThat(memes[0].subtitle()).contains("0 minutes")
    }

    @Test
    fun testSubtitleContainsSubreddit() {
        assertThat(memes[0].subtitle()).contains(memes[0].subreddit)
    }

    @Test
    fun testSubtitleContainsTimeElapsedDays() {
        val meme = memes[0].copy(createdUtc = Instant.now().minusSeconds(86400).epochSecond)
        assertThat(meme.subtitle()).contains("1 days")
    }

    @Test
    fun testSubtitleContainsTimeElapsedHours() {
        val meme = memes[0].copy(createdUtc = Instant.now().minusSeconds(3600).epochSecond)
        assertThat(meme.subtitle()).contains("1 hours")
    }

    @Test
    fun testSubtitleContainsTimeElapsedMinutes() {
        val meme = memes[0].copy(createdUtc = Instant.now().minusSeconds(60).epochSecond)
        assertThat(meme.subtitle()).contains("1 minutes")
    }
}