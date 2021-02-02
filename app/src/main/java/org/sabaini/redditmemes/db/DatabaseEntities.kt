package org.sabaini.redditmemes.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.sabaini.redditmemes.model.Meme

/* Class that represent the database table to store the memes*/
@Entity
data class DatabaseMeme(
        @PrimaryKey
        val id: Long,
        val title: String,
        val author: String,
        val imgUrl: String,
        val created: Long,
        val stickied: Boolean,
        val isVideo: Boolean,
        val permalink: String,
        val position: Int,
        val score: Int,
        val createdUtc: Long,
        val numComments: Int,
        val subreddit: String)

/* Convert database results to domain objects */
fun List<DatabaseMeme>.asDomainModel(): List<Meme> {
    return map {
        Meme(
                id = it.id,
                title = it.title,
                author = it.author,
                imgUrl = it.imgUrl,
                created = it.created,
                stickied = it.stickied,
                isVideo = it.isVideo,
                permalink = it.permalink,
                position = it.position,
                score = it.score,
                createdUtc = it.createdUtc,
                numComments = it.numComments,
                subreddit = it.subreddit
        )
    }
}