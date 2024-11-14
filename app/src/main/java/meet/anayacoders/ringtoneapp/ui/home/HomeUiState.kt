package meet.anayacoders.ringtoneapp.ui.home

import meet.anayacoders.ringtoneapp.domain.model.Song

data class HomeUiState(
    val loading: Boolean? = false,
    val songs: List<Song>? = emptyList(),
    val selectedSong: Song? = null,
    val errorMessage: String? = null
)