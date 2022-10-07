package org.sabaini.redditmemes.core.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val redditMemesAppDispatchers: RedditMemesAppDispatchers)

enum class RedditMemesAppDispatchers {
    IO
}
