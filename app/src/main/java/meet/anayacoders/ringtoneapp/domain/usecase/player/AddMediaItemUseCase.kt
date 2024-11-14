package meet.anayacoders.ringtoneapp.domain.usecase.player

import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.domain.service.MusicController
import javax.inject.Inject

class AddMediaItemUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke(songs: List<Song>){
        musicController.addMediaItems(songs)
    }
}