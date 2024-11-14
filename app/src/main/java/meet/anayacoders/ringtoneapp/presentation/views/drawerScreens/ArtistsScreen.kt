package meet.anayacoders.ringtoneapp.presentation.views.drawerScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import meet.anayacoders.ringtoneapp.ui.theme.msdLabelViewAll
import meet.anayacoders.ringtoneapp.ui.theme.msdSongSingerName
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.ui.home.HomeEvent
import meet.anayacoders.ringtoneapp.ui.home.HomeUiState

@Composable
fun ArtistsScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
    uiState: HomeUiState
) {

    with(uiState) {
        when {
            loading == true -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }

            }

            loading == false && errorMessage == null -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    if (songs != null) {
                        val song = songs.groupBy { it.artist }
                        song.forEach { (artist, songs) ->
                            item {
                                ArtistItem(songs = songs, artist = artist) {
                                }
                            }
                        }
                    }
                }
            }

            errorMessage != null -> {}
        }
    }


}


@Composable
fun ArtistItem(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    artist: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .padding(14.dp),
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = artist,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = msdLabelViewAll
            )
            Text(
                text = "${songs.size} songs",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = msdSongSingerName
            )
        }
        Image(
            modifier = Modifier
                .size(40.dp)
//                .background()
                .padding(12.dp),
            colorFilter = ColorFilter.tint(Color.Gray),
            painter = painterResource(id = R.drawable.ic_dots_menu),
            contentDescription = ""
        )
    }
}