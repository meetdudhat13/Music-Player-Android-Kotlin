package meet.anayacoders.ringtoneapp.domain.usecase.player

import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.domain.service.MusicController
import meet.anayacoders.ringtoneapp.other.PlayerState
import javax.inject.Inject

class SetMediaControllerCallbackUseCase @Inject constructor(
    private val musicController: MusicController
) {
    operator fun invoke(
        callback: (
            playerState: PlayerState,
            currentSong: Song?,
            currentPosition: Long,
            totalDuration: Long,
            isShuffleEnabled: Boolean,
            isRepeatEnabled: Boolean
        ) -> Unit
    ) {
        musicController.mediaControllerCallback = callback
    }
}