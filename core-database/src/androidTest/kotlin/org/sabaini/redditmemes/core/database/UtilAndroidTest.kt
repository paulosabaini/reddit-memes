package org.sabaini.redditmemes.core.database

import java.time.Instant
import org.sabaini.redditmemes.core.database.entity.MemeEntity

object UtilAndroidTest {

    fun getListOfMemes(size: Int): List<MemeEntity> {
        val list = mutableListOf<MemeEntity>()
        for (i in 1..size) {
            list.add(
                MemeEntity(
                    i.toLong(),
                    "title",
                    "author",
                    "image",
                    false,
                    false,
                    "link",
                    i,
                    1000,
                    Instant.now().epochSecond,
                    0,
                    "memes",
                    "name"
                )
            )
        }
        return list
    }
}