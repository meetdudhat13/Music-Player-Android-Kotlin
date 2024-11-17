package meet.anayacoders.ringtoneapp.ui.album

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.ui.home.HomeEvent
import meet.anayacoders.ringtoneapp.ui.home.HomeUiState
import meet.anayacoders.ringtoneapp.ui.theme.msdAlbumSongName

@Composable
fun AlbumScreen(
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    if (songs != null) {
                        val song = songs.groupBy { it.album }

                        song.forEach { (album, songs) ->
                            item {
                                AlbumItem(album = album, song = songs)
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun AlbumItem(modifier: Modifier = Modifier, song: List<Song>, album: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {


        if (song[0].hasAlbumImg) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                model = song[0].albumArtUri,
                contentDescription = ""
            )
        }else{
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,

                //TODO: Replace with album image

                model = R.drawable.demo,
                contentDescription = ""
            )
        }


        Text(
            text = song[0].album,
            maxLines = 2,
            color = Color.White,
            style = msdAlbumSongName,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = song[0].artist,
                color = Color.Gray,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                style = msdAlbumSongName,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${song.size} songs",
                color = Color.Gray,
                style = msdAlbumSongName
            )
        }

    }

}