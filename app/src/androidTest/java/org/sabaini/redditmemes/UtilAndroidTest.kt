package org.sabaini.redditmemes

import org.sabaini.redditmemes.data.local.DatabaseMeme
import java.time.Instant

object UtilAndroidTest {

    fun getListOfMemes(size: Int): List<DatabaseMeme> {
        val list = mutableListOf<DatabaseMeme>()
        for (i in 1..size) {
            list.add(
                DatabaseMeme(
                    i.toLong(),
                    "title",
                    "author",
                    "image",
                    false,
                    false,
                    "link",
                    i,
                    1000,
                    Instant.now().epochSecond,
                    0,
                    "memes",
                    "name"
                )
            )
        }
        return list
    }
}