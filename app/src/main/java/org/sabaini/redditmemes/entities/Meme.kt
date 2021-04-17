package org.sabaini.redditmemes.entities

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
        return "$author • ${getTimeElapsed()} • $subreddit"
    }

    private fun getTimeElapsed(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateCreated = sdf.parse(getFormattedDate(createdUtc * 1000L))
        val dateNow = sdf.parse(getFormattedDate(System.currentTimeMillis()))

        val diff = dateNow!!.time - dateCreated!!.time

        val diffDays = (diff / (24 * 60 * 60 * 1000)).toInt()
        val diffHours = (diff / (60 * 60 * 1000)).toInt()
        val diffMin = (diff / (60 * 1000)).toInt()

        return when {
            diffDays >= 1 -> {
                "around $diffDays days ago"
            }
            diffHours >= 1 -> {
                "around $diffHours hours ago"
            }
            else -> {
                "around $diffMin minutes ago"
            }
        }
    }

    private fun getFormattedDate(milli: Long): String {
        val date = Date(milli)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
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