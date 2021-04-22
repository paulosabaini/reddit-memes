package org.sabaini.redditmemes

import org.sabaini.redditmemes.data.local.DatabaseMeme
import java.time.Instant

object UtilAndroidTest {

    private val meme1 = DatabaseMeme(
        1,
        "title",
        "author",
        "image",
        false,
        false,
        "link",
        1,
        1000,
        Instant.now().epochSecond,
        0,
        "memes"
    )

    private val meme2 = DatabaseMeme(
        2,
        "title2",
        "author2",
        "image2",
        false,
        false,
        "link2",
        2,
        1000,
        Instant.now().epochSecond,
        0,
        "memes"
    )

    val memes = listOf(meme1, meme2)
}