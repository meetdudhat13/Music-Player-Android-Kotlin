package meet.anayacoders.ringtoneapp.ui.home

import meet.anayacoders.ringtoneapp.domain.model.Song

sealed class HomeEvent {
    object PlaySong : HomeEvent()
    object PauseSong : HomeEvent()
    object ResumeSong : HomeEvent()
    object FetchAllSong : HomeEvent()
    object SkipToNextSong : HomeEvent()
    object SkipToPreviousSong : HomeEvent()
    data class OnArtistClick(val artist: String) : HomeEvent()
    data class OnAlbumSelected(val album: String) : HomeEvent()
    data class OnSongSelected(val selectedSong: Song) : HomeEvent()
}