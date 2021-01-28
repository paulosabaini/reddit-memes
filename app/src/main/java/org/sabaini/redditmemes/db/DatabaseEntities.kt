package org.sabaini.redditmemes.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.sabaini.redditmemes.model.Meme

@Entity
data class DatabaseMeme(
    @PrimaryKey
    val id: String,
    val title: String,
    val author: String,
    val imgUrl: String,
    val created: Long,
    val stickied: Boolean,
    val isVideo: Boolean,
    val permalink: String,
    val score: Int)

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
            score = it.score
        )
    }
}