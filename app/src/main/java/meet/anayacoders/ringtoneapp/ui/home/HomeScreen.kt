package meet.anayacoders.ringtoneapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.compose.albumHeadingGreen
import com.example.compose.gradient1End
import com.example.compose.gradient1start
import com.example.compose.labelViewAllGrayColor
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.ui.theme.msdAlbumName
import meet.anayacoders.ringtoneapp.ui.theme.msdAlbumOwnerName
import meet.anayacoders.ringtoneapp.ui.theme.msdLabelHeading
import meet.anayacoders.ringtoneapp.ui.theme.msdLabelViewAll
import meet.anayacoders.ringtoneapp.ui.theme.msdNewAlbumLabel
import meet.anayacoders.ringtoneapp.ui.theme.msdSongSingerName

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onEvent: (HomeEvent) -> Unit, uiState: HomeUiState) {
//    LazyColumn(
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
//        item {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),


            ) {
            //            albumList.forEach { item ->
            AlbumItemHomeScreen()
            AlbumItemHomeScreen()
            AlbumItemHomeScreen()
            AlbumItemHomeScreen()
            //            }
        }
//        }
//        item {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Top genres", style = msdLabelHeading)
            Text(text = "VIEW ALL", style = msdLabelViewAll, color = labelViewAllGrayColor)
        }
//        }
//        item {


        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),


            ) {
            //            albumList.forEach { item ->
            GenresItemHomeScreen()
            GenresItemHomeScreen()
            GenresItemHomeScreen()
            GenresItemHomeScreen()
            GenresItemHomeScreen()
            //            }
        }
//        }
//        item {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Trending", style = msdLabelHeading)
            Text(text = "VIEW ALL", style = msdLabelViewAll, color = labelViewAllGrayColor)
        }
//        }


        with(uiState) {
            when {
                loading == true -> {
//                    item {

                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .size(100.dp)
                                .fillMaxHeight()
                                .align(Alignment.Center)
                                .padding(
                                    top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp
                                )
                        )
                    }
                }

//                }

                loading == false && errorMessage == null -> {
                    if (songs != null) {

//                        items(songs) {
                        LazyColumn(
                            modifier = Modifier.height(88.dp * (songs.size)),
                            userScrollEnabled = false
                        ) {
                            items(songs) {
                                SongItemHomeScreen(
                                    song = it,
                                    playerState = uiState.selectedSong == it,
                                ) {
                                    onEvent(HomeEvent.OnSongSelected(it))
                                    onEvent(HomeEvent.PlaySong)
                                }
                            }

                        }
                    }
//                    }
                }

                errorMessage != null -> {}
            }
        }
    }
}

@Composable
fun AlbumItemHomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .height(280.dp)
            .width(260.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.demo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
//            colorFilter = ColorFilter.tint(Color(0f, 0f, 0f, 0.5f), blendMode = BlendMode.Darken),
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)),
        ) {

        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "NEW ALBUM",
                textAlign = TextAlign.End,
                style = msdNewAlbumLabel,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.End)
                    .clip(
                        RoundedCornerShape(80.dp)
                    )
                    .background(albumHeadingGreen)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
            Column(
                modifier = Modifier
//                    .background(Color.Magenta)
                    .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Album Name", style = msdAlbumName, color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.demo),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(
                                CircleShape
                            ),
                    )
                    Text(
                        text = "Album Author", color = Color.White, style = msdAlbumOwnerName
                    )
                }
            }
        }
    }
}

@Composable
fun GenresItemHomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(180.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.demo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(gradient1start, gradient1End),
                        0f,
                        500f,
                    )
                )
        ) {}
        Text(
            text = "Pop",
            textAlign = TextAlign.End,
            color = Color.White,
            style = msdLabelViewAll,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
fun SongItemHomeScreen(
    modifier: Modifier = Modifier,
    song: Song,
    playerState: Boolean,
    onClick: () -> Unit
) {

    val painter = rememberAsyncImagePainter(
        if (playerState) {
            R.drawable.ic_pause_btn
        } else {
            R.drawable.ic_play_btn
        }
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            onClick()
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            if (song.hasAlbumImg) {
                AsyncImage(
                    modifier = Modifier.size(72.dp),
                    contentScale = ContentScale.Crop,
                    model = song.albumArtUri,
                    contentDescription = ""
                )
            } else {
                AsyncImage(
                    modifier = Modifier.size(72.dp),
                    contentScale = ContentScale.Crop,

                    //TODO: Add Placeholder Image here....

                    model = R.drawable.demo,
                    contentDescription = ""
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = song.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = msdLabelViewAll
                )
                Text(
                    text = song.artist,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = msdSongSingerName
                )
            }
            Image(
                modifier = Modifier.size(40.dp),
                painter = painter,
                contentDescription = ""
            )
        }
    }
}