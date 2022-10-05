package org.sabaini.redditmemes.data.remote

import android.util.Log
import com.google.gson.annotations.SerializedName
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
    @SerializedName("url_overridden_by_dest")
    val imgUrl: String,
    val stickied: Boolean,
    val id: String,
    @SerializedName("is_video")
    val isVideo: Boolean,
    val permalink: String,
    val score: Int,
    @SerializedName("created_utc")
    val createdUtc: Long,
    @SerializedName("num_comments")
    val numComments: Int,
    val subreddit: String,
    val name: String
)

/* Convert api results to database objects */
fun Listing.asDatabaseMeme(position: Int?): Array<DatabaseMeme> {
    var p = position
    return data.children.mapIndexed { i, it ->
        if (p == null) p = i else p = p!! + 1
        DatabaseMeme(
            id = BigInteger(it.data.id, 36).toLong(),
            title = it.data.title,
            author = it.data.author,
            imgUrl = it.data.imgUrl,
            stickied = it.data.stickied,
            isVideo = it.data.isVideo,
            permalink = it.data.permalink,
            position = p!!,
            score = it.data.score,
            createdUtc = it.data.createdUtc,
            numComments = it.data.numComments,
            subreddit = it.data.subreddit,
            name = it.data.name
        )
    }.toTypedArray()
}