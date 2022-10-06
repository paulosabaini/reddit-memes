package org.sabaini.redditmemes.core.model

import java.time.Instant

fun getListOfMemes(size: Int): MutableList<Meme> {
    val list = mutableListOf<Meme>()
    for (i in 1..size) {
        list.add(
            Meme(
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
                "name$i"
            )
        )
    }
    return list
}