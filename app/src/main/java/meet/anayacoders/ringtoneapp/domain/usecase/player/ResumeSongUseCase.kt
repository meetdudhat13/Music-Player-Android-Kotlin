package meet.anayacoders.ringtoneapp.domain.usecase.player

import meet.anayacoders.ringtoneapp.domain.service.MusicController
import javax.inject.Inject

class ResumeSongUseCase @Inject constructor(private val musicController: MusicController){
    operator fun invoke() = musicController.resume()
}