package meet.anayacoders.ringtoneapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.ui.home.HomeEvent
import meet.anayacoders.ringtoneapp.ui.home.HomeUiState
import meet.anayacoders.ringtoneapp.ui.home.SongItemHomeScreen

@Composable
fun CategorizedSongScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
    uiState: HomeUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LazyColumn {
            item {
                Column {
                    AsyncImage(
                        model = R.drawable.demo,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = size.height / 3,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            items(uiState.selectedFilter ?: emptyList()) { song ->
                SongItemHomeScreen(song = song, playerState = uiState.selectedSong == song) {
                    onEvent(HomeEvent.OnSongSelected(song))
                    onEvent(HomeEvent.PlaySong)
                }
            }
        }
    }
}