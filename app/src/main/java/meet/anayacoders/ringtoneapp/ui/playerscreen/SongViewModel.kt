package meet.anayacoders.ringtoneapp.ui.playerscreen

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import meet.anayacoders.ringtoneapp.domain.usecase.player.PauseSongUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.ResumeSongUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.SeekSongToPositionUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.SkipToNextSongUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.SkipToPreviousSongUseCase
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val pauseSongUseCase: PauseSongUseCase,
    private val resumeSongUseCase: ResumeSongUseCase,
    private val skipToNextSongUseCase: SkipToNextSongUseCase,
    private val skipToPreviousSongUseCase: SkipToPreviousSongUseCase,
    private val seekToPositionUseCase: SeekSongToPositionUseCase,
) : ViewModel() {

    fun onEvent(event: SongEvent) {
        when (event) {
            SongEvent.PauseSong -> pauseMusic()
            SongEvent.ResumeSong -> resumeMusic()
            is SongEvent.SeekSongToPosition -> seekToPosition(event.position)
            SongEvent.SkipToNextSong ->skipToNextSong()
            SongEvent.SkipToPreviousSong -> skipToPreviousSong()
        }
    }

    private fun pauseMusic() {
        pauseSongUseCase()
    }

    private fun resumeMusic() {
        resumeSongUseCase()
    }

    private fun skipToNextSong() = skipToNextSongUseCase {

    }

    private fun skipToPreviousSong() = skipToPreviousSongUseCase {

    }

    private fun seekToPosition(position: Long) {
        seekToPositionUseCase(position)
    }

}