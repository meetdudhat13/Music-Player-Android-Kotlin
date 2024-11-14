package meet.anayacoders.ringtoneapp.domain.usecase.player

import meet.anayacoders.ringtoneapp.domain.repository.MusicRepository
import javax.inject.Inject

class GetSongsUseCase @Inject constructor(
    private val musicRepository: MusicRepository
){
    operator fun invoke() = musicRepository.getSongs()
}