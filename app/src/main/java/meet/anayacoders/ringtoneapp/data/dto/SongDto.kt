package meet.anayacoders.ringtoneapp.data.dto

data class SongDto(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long, // Duration in milliseconds
    val data: String, // File path to the audio file
    val albumId: Long?, // Album ID for retrieving album art
    val albumArtUri: String?, // URI for the album art (optional)
    val displayName: String, // Display name of the audio file
    val size: Long, // Size of the audio file in bytes
    val dateAdded: Long // Date when the audio file was added (in milliseconds)
)
