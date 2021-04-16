package org.sabaini.redditmemes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.sabaini.redditmemes.entities.Meme

/* Class that represent the database table to store the memes*/
@Entity
data class DatabaseMeme(
        @PrimaryKey
        val id: Long,
        val title: String,
        val author: String,
        val imgUrl: String,
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