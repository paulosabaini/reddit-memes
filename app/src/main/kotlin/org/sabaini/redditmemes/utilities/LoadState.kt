package org.sabaini.redditmemes.utilities

sealed class LoadState {
    object Loading : LoadState()
    object Done : LoadState()
}
