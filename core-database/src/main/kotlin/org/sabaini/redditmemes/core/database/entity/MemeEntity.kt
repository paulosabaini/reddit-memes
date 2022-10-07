package org.sabaini.redditmemes.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.sabaini.redditmemes.core.model.Meme

@Entity
data class MemeEntity(
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
    val subreddit: String,
    val name: String
)

fun List<MemeEntity>.asExternalModel(): List<Meme> {
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
            subreddit = it.subreddit,
            name = it.name
        )
    }
}
