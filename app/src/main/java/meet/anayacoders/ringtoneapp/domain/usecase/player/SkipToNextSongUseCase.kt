package meet.anayacoders.ringtoneapp.domain.usecase.player

import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.domain.service.MusicController
import javax.inject.Inject

class SkipToNextSongUseCase @Inject constructor(
    private val musicController: MusicController
) {
    operator fun invoke(updateHomeUi: (Song?) -> Unit) {
        musicController.skipToNextSong()
        updateHomeUi(musicController.getCurrentSong())
    }
}