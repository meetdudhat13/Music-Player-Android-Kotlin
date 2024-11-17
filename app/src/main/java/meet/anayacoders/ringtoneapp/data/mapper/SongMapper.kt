package meet.anayacoders.ringtoneapp.data.mapper

import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import meet.anayacoders.ringtoneapp.data.dto.SongDto
import meet.anayacoders.ringtoneapp.domain.model.Song

fun SongDto.toSong() =
    Song(
        id = id,
        title = title,
        hasAlbumImg = hasAlbumImg,
        artist = artist,
        album = album,
        duration = duration,
        data = data,
        albumId = albumId,
        albumArtUri = albumArtUri,
        displayName = displayName,
        size = size,
        dateAdded = dateAdded
    )


@OptIn(UnstableApi::class)
fun MediaItem.toSong() : Song{
    val hasAlbumImg = mediaMetadata.artworkUri != null

    return   Song(
        id = mediaId.toLongOrNull() ?: 0L, // Convert mediaId to Long, default to 0 if parsing fails
        title = mediaMetadata.title.toString(),
        hasAlbumImg = hasAlbumImg,
        artist = mediaMetadata.artist.toString(),
        album = mediaMetadata.albumTitle.toString(),
        duration = mediaMetadata.durationMs ?: 0L,
        data = mediaId, // Assuming mediaId represents the file path or can be used to identify the file
        albumId = null, // If you have a way to get album ID, set it here, otherwise use null
        albumArtUri = mediaMetadata.artworkUri?.toString(),
        displayName = mediaMetadata.displayTitle.toString(),
        size = 0L, // Size is not available from MediaItem directly, set to 0 or fetch separately if needed
        dateAdded = 0L // Date added is also not available, set to 0 or fetch separately if needed
    )
}
