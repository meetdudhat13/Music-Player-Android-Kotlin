package meet.anayacoders.ringtoneapp.other

import meet.anayacoders.ringtoneapp.domain.model.Song

data class MusicControllerUiState(
    val playerState: PlayerState? = null,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val totalDuration: Long = 0L,
    val isShuffleEnabled: Boolean = false,
    val isRepeatOneEnable: Boolean = false,
    val isAppBarVisible : Boolean = true
)