package org.sabaini.redditmemes.core.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Instant

class MemeTest {

    val meme = getListOfMemes(1)[0]

    @Test
    fun testGetNumComments() {
        assertThat(meme.getNumCommentsString()).isEqualTo("${meme.numComments} comments")
    }

    @Test
    fun testGetPoints() {
        assertThat(meme.getPoints()).isEqualTo("1,000 points")
    }

    @Test
    fun testSubtitleContainsAuthor() {
        assertThat(meme.subtitle()).contains(meme.author)
    }

    @Test
    fun testSubtitleContainsTimeElapsed() {
        assertThat(meme.subtitle()).contains("0 minutes")
    }

    @Test
    fun testSubtitleContainsSubreddit() {
        assertThat(meme.subtitle()).contains(meme.subreddit)
    }

    @Test
    fun testSubtitleContainsTimeElapsedDays() {
        val meme = meme.copy(createdUtc = Instant.now().minusSeconds(86400).epochSecond)
        assertThat(meme.subtitle()).contains("1 days")
    }

    @Test
    fun testSubtitleContainsTimeElapsedHours() {
        val meme = meme.copy(createdUtc = Instant.now().minusSeconds(3600).epochSecond)
        assertThat(meme.subtitle()).contains("1 hours")
    }

    @Test
    fun testSubtitleContainsTimeElapsedMinutes() {
        val meme = meme.copy(createdUtc = Instant.now().minusSeconds(60).epochSecond)
        assertThat(meme.subtitle()).contains("1 minutes")
    }
}