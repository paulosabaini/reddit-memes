package org.sabaini.redditmemes

import org.sabaini.redditmemes.data.local.DatabaseMeme
import org.sabaini.redditmemes.data.remote.Listing
import org.sabaini.redditmemes.data.remote.ListingData
import org.sabaini.redditmemes.data.remote.Post
import org.sabaini.redditmemes.data.remote.PostData
import org.sabaini.redditmemes.entities.Meme
import java.time.Instant

object Util {

    val response = Listing(
        "", ListingData(
            listOf(
                Post(
                    PostData(
                        "title",
                        "author",
                        "image",
                        false,
                        "1",
                        false,
                        "link",
                        1000,
                        Instant.now().epochSecond,
                        0,
                        "memes",
                        "name1"
                    )
                ), Post(
                    PostData(
                        "title",
                        "author",
                        "image",
                        false,
                        "2",
                        false,
                        "link",
                        1000,
                        Instant.now().epochSecond,
                        0,
                        "memes",
                        "name2"
                    )
                )
            )
        )
    )

    fun getListOfMemes(size: Int): MutableList<Meme> {
        val list = mutableListOf<Meme>()
        for (i in 1..size) {
            list.add(
                Meme(
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
                    "name$i"
                )
            )
        }
        return list
    }

    fun getListOfDatabaseMemes(size: Int): MutableList<DatabaseMeme> {
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
                    "name$i"
                )
            )
        }
        return list
    }
}