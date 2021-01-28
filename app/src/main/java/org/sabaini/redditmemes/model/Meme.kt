package org.sabaini.redditmemes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
data class Meme(
    val id: String,
    val title: String,
    val author: String,
    val imgUrl: String,
    val created: Long,
    val stickied: Boolean,
    val isVideo: Boolean,
    val permalink: String
): Parcelable {
    fun description(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = java.util.Date(created * 1000)

        return "Posted by " + author + " on " + sdf.format(date)
    }
}