package meet.anayacoders.ringtoneapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import meet.anayacoders.ringtoneapp.domain.usecase.player.AddMediaItemUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.GetSongsUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.PauseSongUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.PlaySongUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.ResumeSongUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.SkipToNextSongUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.player.SkipToPreviousSongUseCase
import meet.anayacoders.ringtoneapp.other.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSongsUseCase: GetSongsUseCase,
    private val addMediaItemsUseCase: AddMediaItemUseCase,
    private val playSongUseCase: PlaySongUseCase,
    private val pauseSongUseCase: PauseSongUseCase,
    private val resumeSongUseCase: ResumeSongUseCase,
    private val skipToNextSongUseCase: SkipToNextSongUseCase,
    private val skipToPreviousSongUseCase: SkipToPreviousSongUseCase,
) : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
        private set



    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.PlaySong -> playSong()
            HomeEvent.PauseSong -> pauseSong()
            HomeEvent.ResumeSong -> resumeSong()
            HomeEvent.FetchAllSong -> getSong()

            is HomeEvent.OnSongSelected -> homeUiState =
                homeUiState.copy(selectedSong = event.selectedSong)

            is HomeEvent.OnArtistClick -> getSongByArtist(event.artist)
            is HomeEvent.OnAlbumSelected -> getSongByAlbum(event.album)

            is HomeEvent.SkipToNextSong -> skipToNextSong()
            is HomeEvent.SkipToPreviousSong -> skipToPreviousSong()

        }
    }

    private fun getSong() {
        homeUiState = homeUiState.copy(loading = true)

        viewModelScope.launch {
            getSongsUseCase().catch {
                homeUiState = homeUiState.copy(
                    loading = false,
                    errorMessage = it.message
                )
            }.collect {
                homeUiState = when (it) {
                    is Resource.Success -> {
                        it.data?.let { songs ->
                            addMediaItemsUseCase(songs)

                        }
                        homeUiState.copy(
                            loading = false,
                            songs = it.data
                        )
                    }

                    is Resource.Loading -> {
                        homeUiState.copy(
                            loading = true,
                            errorMessage = null
                        )
                    }

                    is Resource.Error -> {
                        homeUiState.copy(
                            loading = false,
                            errorMessage = it.message
                        )
                    }
                }
            }
        }
    }

    private fun getSongByArtist( artist: String){
        homeUiState.selectedFilter = homeUiState.songs?.filter { it.artist == artist }
    }

    private fun getSongByAlbum( album: String){
        homeUiState.selectedFilter = homeUiState.songs?.filter { it.album == album }
    }

    private fun playSong() {
        homeUiState.apply {
            songs?.indexOf(selectedSong)?.let { song ->
                playSongUseCase(song)
            }
        }
    }

    private fun pauseSong() = pauseSongUseCase()

    private fun resumeSong() = resumeSongUseCase()

    private fun skipToNextSong() = skipToNextSongUseCase {
        homeUiState = homeUiState.copy(selectedSong = it)
    }

    private fun skipToPreviousSong() = skipToPreviousSongUseCase {
        homeUiState = homeUiState.copy(selectedSong = it)
    }


}