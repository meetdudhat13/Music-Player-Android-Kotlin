package meet.anayacoders.ringtoneapp.domain.repository

import kotlinx.coroutines.flow.Flow
import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.other.Resource

interface MusicRepository {
    fun getSongs(): Flow<Resource<List<Song>>>

//    fun getSongsByArtist():Flow<Resource<List<Song>>>
}