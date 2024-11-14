package meet.anayacoders.ringtoneapp.di

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import meet.anayacoders.ringtoneapp.data.remotedatabase.MusicRemoteDatabase
import meet.anayacoders.ringtoneapp.data.repository.MusicRepositoryImpl
import meet.anayacoders.ringtoneapp.data.service.MusicControllerImpl
import meet.anayacoders.ringtoneapp.domain.repository.MusicRepository
import meet.anayacoders.ringtoneapp.domain.service.MusicController
import meet.anayacoders.ringtoneapp.other.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCollection() = FirebaseFirestore.getInstance().collection(Constants.SONG_COLLECTION)

    @Singleton
    @Provides
    fun provideMusicDatabase(songCollection: CollectionReference) =
        MusicRemoteDatabase(songCollection)


    @Singleton
    @Provides
    fun provideMusicRepository(
        musicRemoteDatabase: MusicRemoteDatabase,
        @ApplicationContext context: Context
    ): MusicRepository =
        MusicRepositoryImpl(musicRemoteDatabase, context)

    @Singleton
    @Provides
    fun provideMusicController(@ApplicationContext context: Context): MusicController =
        MusicControllerImpl(context)


}