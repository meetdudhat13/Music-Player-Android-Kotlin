package meet.anayacoders.ringtoneapp.data.repository

import meet.anayacoders.ringtoneapp.data.local.dao.PlaylistDao
import meet.anayacoders.ringtoneapp.data.local.entity.PlaylistEntity
import javax.inject.Inject

class PlaylistRepository @Inject constructor(private val playlistDao: PlaylistDao) {

    suspend fun getAllPlaylist(): List<PlaylistEntity> = playlistDao.getAllPlaylists()

    suspend fun insertPlaylist(playlist: PlaylistEntity) = playlistDao.insertPlaylist(playlist)

    suspend fun deletePlaylist(playlist: PlaylistEntity) = playlistDao.deletePlaylist(playlist)

}