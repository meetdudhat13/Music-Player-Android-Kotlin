package meet.anayacoders.ringtoneapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import meet.anayacoders.ringtoneapp.domain.model.Song


@Entity(tableName = "playlist_table")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val songs: List<Song> = emptyList()
)