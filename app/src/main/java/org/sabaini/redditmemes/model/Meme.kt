package org.sabaini.redditmemes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


/* Model utilized in the views */

@Parcelize
data class Meme(
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
        val subreddit: String
) : Parcelable {
    fun subtitle(): String {
        return "$author • ${getFormattedDate()} • $subreddit"
    }

    fun getFormattedDate(): String {
        val date = Date(createdUtc * 1000L)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        sdf.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)

        return sdf.format(date)
    }

    fun getPoints(): String {
        val dec = DecimalFormat("#,###.##")
        return "${dec.format(score)} points"
    }

    fun getNumComments(): String {
        return "$numComments comments"
    }
}