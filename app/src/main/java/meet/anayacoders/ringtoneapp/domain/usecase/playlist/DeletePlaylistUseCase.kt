package meet.anayacoders.ringtoneapp.domain.usecase.playlist

import meet.anayacoders.ringtoneapp.data.local.entity.PlaylistEntity
import meet.anayacoders.ringtoneapp.data.repository.PlaylistRepository
import javax.inject.Inject

class DeletePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend fun invoke(playlist: PlaylistEntity) {
        repository.deletePlaylist(playlist)
    }
}