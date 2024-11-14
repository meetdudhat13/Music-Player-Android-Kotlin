package meet.anayacoders.ringtoneapp.other

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import meet.anayacoders.ringtoneapp.domain.model.Song

class Converters {

    @TypeConverter
    fun fromSongList(value: List<Song>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Song>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSongList(value: String): List<Song> {
        val gson = Gson()
        val type = object : TypeToken<List<Song>>() {}.type
        return gson.fromJson(value, type)
    }
}