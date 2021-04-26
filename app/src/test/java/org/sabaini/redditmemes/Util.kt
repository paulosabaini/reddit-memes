package org.sabaini.redditmemes

import org.sabaini.redditmemes.data.local.DatabaseMeme
import org.sabaini.redditmemes.data.remote.Listing
import org.sabaini.redditmemes.data.remote.ListingData
import org.sabaini.redditmemes.data.remote.Post
import org.sabaini.redditmemes.data.remote.PostData
import org.sabaini.redditmemes.entities.Meme
import java.time.Instant

object Util {

    private val meme1 = Meme(
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
        "memes",
        "name"
    )

    private val meme2 = Meme(
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
        "memes",
        "name"
    )

    val memes = listOf(meme1, meme2)

    private val dbMeme1 = DatabaseMeme(
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
        "memes",
        "name"
    )

    private val dbMeme2 = DatabaseMeme(
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
        "memes",
        "name"
    )

    val dbMemes = listOf(dbMeme1, dbMeme2)

    val response = Listing(
        "", ListingData(
            listOf(
                Post(
                    PostData(
                        dbMeme1.title,
                        dbMeme1.author,
                        dbMeme1.imgUrl,
                        dbMeme1.stickied,
                        dbMeme1.id.toString(),
                        dbMeme1.isVideo,
                        dbMeme1.permalink,
                        dbMeme1.score,
                        dbMeme1.createdUtc,
                        dbMeme1.numComments,
                        dbMeme1.subreddit,
                        dbMeme1.name
                    )
                ), Post(
                    PostData(
                        dbMeme2.title,
                        dbMeme2.author,
                        dbMeme2.imgUrl,
                        dbMeme2.stickied,
                        dbMeme2.id.toString(),
                        dbMeme2.isVideo,
                        dbMeme2.permalink,
                        dbMeme2.score,
                        dbMeme2.createdUtc,
                        dbMeme2.numComments,
                        dbMeme2.subreddit,
                        dbMeme2.name
                    )
                )
            )
        )
    )
}