package meet.anayacoders.ringtoneapp.ui.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import meet.anayacoders.ringtoneapp.ui.theme.msdLabelViewAll
import meet.anayacoders.ringtoneapp.ui.theme.msdSongSingerName
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.other.PlayerState
import meet.anayacoders.ringtoneapp.ui.home.HomeEvent

//@Composable
//fun HomeBottomBar(
//    modifier: Modifier = Modifier,
//    onEvent: (HomeEvent) -> Unit,
//    playerState: PlayerState?,
//    song: Song?,
//    onBarClick: () -> Unit
//) {
//
//        var offsetX by remember { mutableFloatStateOf(0f) }
//
//        AnimatedVisibility(
//            visible = playerState != PlayerState.STOPPED,
//            modifier = modifier
//        ) {
//            if (song != null) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .pointerInput(Unit) {
//                            detectDragGestures(
//                                onDragEnd = {
//                                    when {
//                                        offsetX > 0 -> {
//                                            onEvent(HomeEvent.SkipToPreviousSong)
//                                        }
//
//                                        offsetX < 0 -> {
//                                            onEvent(HomeEvent.SkipToNextSong)
//                                        }
//                                    }
//                                },
//                                onDrag = { change, dragAmount ->
//                                    change.consume()
//                                    val (x, _) = dragAmount
//                                    offsetX = x
//                                }
//                            )
//
//                        }
//                        .background(
//                            if (!isSystemInDarkTheme()) {
//                                Color.LightGray
//                            } else Color.DarkGray
//                        ),
//                ) {
//                    HomeBottomBarItem(
//                        song = song,
//                        onEvent = onEvent,
//                        playerState = playerState,
//                        onBarClick = onBarClick
//                    )
//                }
//            }
//        }
//
//}


//        if (song != null) {
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color(0xFF000000))
//                    .padding(bottom = 16.dp, end = 16.dp)
//                    .clickable {
//                        onBarClick()
//                    }
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    AsyncImage(
//                        modifier = Modifier.size(72.dp),
//                        contentScale = ContentScale.Crop,
//                        model = song.imageUrl,
//                        contentDescription = ""
//                    )
//                    Column(
//                        modifier = Modifier
//                            .padding(horizontal = 8.dp)
//                            .weight(1f),
//                        verticalArrangement = Arrangement.spacedBy(4.dp)
//                    ) {
//                        Text(
//                            text = song.title,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis,
//                            style = msdLabelViewAll
//                        )
//                        Text(
//                            text = song.subtitle,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis,
//                            style = msdSongSingerName
//                        )
//                    }
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(16.dp),
//                        //                    modifier = Modifier.padding(end = 24.dp)
//                    ) {
//                        //                    Image(
//                        //                        modifier = Modifier.size(16.dp),
//                        //                        painter = painterResource(id = R.drawable.ic_prev_song),
//                        //                        colorFilter = ColorFilter.tint(
//                        //                            Color.White
//                        //                        ),
//                        //                        contentDescription = ""
//                        //                    )
//
//                        val painter = rememberAsyncImagePainter(
//                            if (playerState == PlayerState.PLAYING) {
//                                R.drawable.ic_pause_btn
//                            } else {
//                                R.drawable.ic_play_btn
//                            }
//                        )
//                        Image(
//                            modifier = Modifier
//                                .size(40.dp)
//                                .clickable(
////                                    interactionSource = remember {
////                                        MutableInteractionSource()
////                                    },
////                                    indication = rememberRipple(
////                                        bounded = false,
////                                        radius = 24.dp
////                                    )
//                                ) {
//                                    if (playerState == PlayerState.PLAYING) {
//                                        onEvent(HomeEvent.PlaySong)
//                                    } else {
//                                        onEvent(HomeEvent.PauseSong)
//                                    }
//                                },
//                            painter = painter,
//                            contentDescription = ""
//                        )
//                        Image(
//                            modifier = Modifier
//                                .size(22.dp),
//
//                            colorFilter = ColorFilter.tint(
//                                Color.White
//                            ),
//                            painter = painterResource(id = R.drawable.ic_next_song),
//                            contentDescription = ""
//                        )
//                    }
//                }
//            }
//        }
//    }
//}


//****************************

@Composable
fun HomeBottomBar(
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
    playerState: PlayerState?,
    song: Song?,
    onBarClick: () -> Unit
) {

    var offsetX by remember { mutableFloatStateOf(0f) }

    AnimatedVisibility(
        visible = playerState != PlayerState.STOPPED,
        modifier = modifier
            .background(Color(0xFF000000))
    ) {
        if (song != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                when {
                                    offsetX > 0 -> {
                                        onEvent(HomeEvent.SkipToPreviousSong)
                                    }

                                    offsetX < 0 -> {
                                        onEvent(HomeEvent.SkipToNextSong)
                                    }
                                }
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                val (x, _) = dragAmount
                                offsetX = x
                            }
                        )

                    }

            ) {
                HomeBottomBarItem(
                    song = song,
                    onEvent = onEvent,
                    playerState = playerState,
                    onBarClick = onBarClick
                )
            }
        }
    }
}


@Composable
fun HomeBottomBarItem(
    song: Song,
    onEvent: (HomeEvent) -> Unit,
    playerState: PlayerState?,
    onBarClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(64.dp)
            .clickable(onClick = { onBarClick() })

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(song.albumArtUri),
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .offset(16.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(vertical = 8.dp, horizontal = 32.dp),
            ) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = song.title,
                    style = msdLabelViewAll
                )

                Text(
                    text = song.artist,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = msdSongSingerName,
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = 0.60f
                        }

                )
            }
            val painter = rememberAsyncImagePainter(
                if (playerState == PlayerState.PLAYING) {
                    R.drawable.ic_pause_btn
                } else {
                    R.drawable.ic_play_btn
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(end = 16.dp)
            ) {

                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(
//                                    interactionSource = remember {
//                                        MutableInteractionSource()
//                                    },
//                                    indication = rememberRipple(
//                                        bounded = false,
//                                        radius = 24.dp
//                                    )
                        ) {
                            if (playerState == PlayerState.PAUSED) {
                                onEvent(HomeEvent.ResumeSong)
                            } else {
                                onEvent(HomeEvent.PauseSong)
                            }
                        },
                    painter = painter,
                    contentDescription = ""
                )
                Image(
                    modifier = Modifier
                        .size(22.dp),

                    colorFilter = ColorFilter.tint(
                        Color(0xFFD5D5D5)
                    ),
                    painter = painterResource(id = R.drawable.ic_next_song),
                    contentDescription = ""
                )
            }
        }
    }
}

