package meet.anayacoders.ringtoneapp.data.repository

import android.content.ContentUris
import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import meet.anayacoders.ringtoneapp.data.dto.SongDto
import meet.anayacoders.ringtoneapp.data.mapper.toSong
import meet.anayacoders.ringtoneapp.data.remotedatabase.MusicRemoteDatabase
import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.domain.repository.MusicRepository
import meet.anayacoders.ringtoneapp.other.Resource
import javax.inject.Inject


class MusicRepositoryImpl @Inject constructor(
    private val musicRemoteDatabase: MusicRemoteDatabase,
    private val context: Context
) :
    MusicRepository {
    override fun getSongs(): Flow<Resource<List<Song>>> =
        flow {
//            val songs = musicRemoteDatabase.getAllSongs().await()
//                .toObjects(SongDto::class.java) as List<SongDto>

            val audioList = mutableListOf<SongDto>()
            val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DATE_ADDED
            )

            val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"


            val cursor = context.contentResolver.query(uri, projection, null, null,sortOrder)

            cursor?.use {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val albumColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                val albumIdColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
                val displayNameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
                val dateAddedColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_ADDED)

                while (it.moveToNext()) {
                    val id = it.getLong(idColumn)
                    val title = it.getString(titleColumn)
                    val artist = it.getString(artistColumn)
                    val album = it.getString(albumColumn)
                    val duration = it.getLong(durationColumn)
                    val data = it.getString(dataColumn)
                    val albumId = it.getLong(albumIdColumn)
                    val displayName = it.getString(displayNameColumn)
                    val size = it.getLong(sizeColumn)
                    val dateAdded = it.getLong(dateAddedColumn)

                    // Construct album art URI using album ID
                    val albumArtUri = ContentUris.withAppendedId(
                        Uri.parse("content://media/external/audio/albumart"),
                        albumId
                    ).toString()

                    val hasAlbumArt = hasAlbumArt(data)


                    val audioFile = SongDto(
                        id = id,
                        title = title,
                        artist = artist,
                        hasAlbumImg = hasAlbumArt,
                        album = album,
                        duration = duration,
                        data = data,
                        albumId = albumId,
                        albumArtUri = if (hasAlbumArt) albumArtUri else "",
                        displayName = displayName,
                        size = size,
                        dateAdded = dateAdded
                    )
                    audioList.add(audioFile)
                }
            }

            if (audioList.isNotEmpty()) {
                emit(Resource.Success(audioList.map { it.toSong() }))
            }
        }
}
fun hasAlbumArt(songPath: String): Boolean {
    val retriever = MediaMetadataRetriever()
    return try {
        retriever.setDataSource(songPath)
        val albumArt = retriever.embeddedPicture
        albumArt != null // Returns true if album art exists
    } catch (e: Exception) {
        false // In case of any error
    } finally {
        retriever.release()
    }
}