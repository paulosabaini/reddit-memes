package org.sabaini.redditmemes.api

import com.squareup.moshi.Json
import org.sabaini.redditmemes.db.DatabaseMeme
import org.sabaini.redditmemes.model.Meme

data class Listing (
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
    val created: Long,
    val stickied: Boolean,
    val id: String,
    @Json(name = "is_video")
    val isVideo: Boolean,
    val permalink: String,
    val score: Int
)

// Convert Network results to domain objects
fun Listing.asDomainModel(): List<Meme> {
    return data.children.map {
        Meme(
            id = it.data.id,
            title = it.data.title,
            author = it.data.author,
            imgUrl = it.data.imgUrl,
            created = it.data.created,
            stickied = it.data.stickied,
            isVideo = it.data.isVideo,
            permalink = it.data.permalink,
            score = it.data.score
        )
    }
}

// Convert Network results to database objects
fun Listing.asDatabaseModel(): Array<DatabaseMeme> {
    return data.children.map {
        DatabaseMeme(
            id = it.data.id,
            title = it.data.title,
            author = it.data.title,
            imgUrl = it.data.imgUrl,
            created = it.data.created,
            stickied = it.data.stickied,
            isVideo = it.data.isVideo,
            permalink = it.data.permalink,
                score = it.data.score
        )
    }.toTypedArray()
}