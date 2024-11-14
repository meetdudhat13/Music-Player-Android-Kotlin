package meet.anayacoders.ringtoneapp.ui.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import meet.anayacoders.ringtoneapp.data.local.entity.PlaylistEntity
import meet.anayacoders.ringtoneapp.domain.usecase.playlist.AddPlaylistUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.playlist.DeletePlaylistUseCase
import meet.anayacoders.ringtoneapp.domain.usecase.playlist.GetPlaylistUseCase
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    private val addPlaylistUseCase: AddPlaylistUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase
): ViewModel() {

    private val _playlist = MutableStateFlow<List<PlaylistEntity>>(emptyList())
    val playlists: StateFlow<List<PlaylistEntity>> = _playlist

    init {
        loadPlaylist()
    }

    private fun loadPlaylist(){
        viewModelScope.launch {
            _playlist.value = getPlaylistUseCase.invoke()
        }
    }

    fun addPlaylist(playlist: PlaylistEntity){
        viewModelScope.launch {
            addPlaylistUseCase.invoke(playlist)
            loadPlaylist()
        }
    }

    fun deletePlaylist(playlist: PlaylistEntity){
        viewModelScope.launch {
            deletePlaylistUseCase.invoke(playlist)
            loadPlaylist()
        }

    }

}