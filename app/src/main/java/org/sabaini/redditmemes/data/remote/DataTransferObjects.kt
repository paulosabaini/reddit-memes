package org.sabaini.redditmemes.data.remote

import com.squareup.moshi.Json
import org.sabaini.redditmemes.data.local.DatabaseMeme
import java.math.BigInteger

/* Objects that represent the JSON from the reddit API */
data class Listing(
        val kind: String,
        val data: ListingData
)

data class ListingData(
        val children: List<Post>
)

data class Post(
        val data: PostData
)

data class PostData(
        val title: String,
        val author: String,
        @Json(name = "url")
        val imgUrl: String,
        val stickied: Boolean,
        val id: String,
        @Json(name = "is_video")
        val isVideo: Boolean,
        val permalink: String,
        val score: Int,
        @Json(name = "created_utc")
        val createdUtc: Long,
        @Json(name = "num_comments")
        val numComments: Int,
        val subreddit: String
)

/* Convert api results to database objects */
fun Listing.asDatabaseModel(): Array<DatabaseMeme> {
    return data.children.mapIndexed { i, it ->
        DatabaseMeme(
                id = BigInteger(it.data.id, 36).toLong(),
                title = it.data.title,
                author = it.data.author,
                imgUrl = it.data.imgUrl,
                stickied = it.data.stickied,
                isVideo = it.data.isVideo,
                permalink = it.data.permalink,
                position = i,
                score = it.data.score,
                createdUtc = it.data.createdUtc,
                numComments = it.data.numComments,
                subreddit = it.data.subreddit
        )
    }.toTypedArray()
}