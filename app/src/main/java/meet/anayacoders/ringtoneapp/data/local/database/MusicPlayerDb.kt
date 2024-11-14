package meet.anayacoders.ringtoneapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import meet.anayacoders.ringtoneapp.data.local.dao.PlaylistDao
import meet.anayacoders.ringtoneapp.data.local.entity.PlaylistEntity
import meet.anayacoders.ringtoneapp.other.Converters

@Database(entities = [PlaylistEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MusicPlayerDb :RoomDatabase(){
    abstract fun playlistDao(): PlaylistDao

}