package meet.anayacoders.ringtoneapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import meet.anayacoders.ringtoneapp.data.local.dao.PlaylistDao
import meet.anayacoders.ringtoneapp.data.local.database.MusicPlayerDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MusicPlayerDb {
        return Room
            .databaseBuilder(
                context,
                MusicPlayerDb::class.java,
                "music_player_db"
            )
            .build()
    }


    @Provides
    fun providePlaylistDao(database: MusicPlayerDb): PlaylistDao {
        return database.playlistDao()
    }
}