package meet.anayacoders.ringtoneapp.ui.playerscreen

import android.media.audiofx.Visualizer
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.OptIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.domain.model.Song
import meet.anayacoders.ringtoneapp.other.MusicControllerUiState
import meet.anayacoders.ringtoneapp.other.PlayerState
import meet.anayacoders.ringtoneapp.other.toTime
import meet.anayacoders.ringtoneapp.ui.theme.bodyFontFamily

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    onEvent: (SongEvent) -> Unit,
    musicControllerUiState: MusicControllerUiState,
    onNavigateUp: () -> Unit,
) {
    if (musicControllerUiState.currentSong != null) {
        Box(modifier = modifier) {
            PlayerScreenBody(
                song = musicControllerUiState.currentSong,
                onNavigateUp = onNavigateUp,
                onEvent = onEvent,
                musicControllerUiState = musicControllerUiState,
            )
        }
    }

}

@Composable
fun PlayerScreenBody(
    song: Song,
    onEvent: (SongEvent) -> Unit,
    musicControllerUiState: MusicControllerUiState,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current

    // Create ExoPlayer instance
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val mediaItem = MediaItem.fromUri(song.data)
//            setMediaItem(mediaItem)
//            prepare()
//            playWhenReady = musicControllerUiState.playerState == PlayerState.PLAYING
//        }
//    }

    // Dispose ExoPlayer when the composable is removed

    // Observe the playback state and update when it changes
    val isPlaying =
        remember { mutableStateOf(musicControllerUiState.playerState == PlayerState.PLAYING) }

    LaunchedEffect(musicControllerUiState.playerState) {
        isPlaying.value = musicControllerUiState.playerState == PlayerState.PLAYING
    }

    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(song.albumArtUri).crossfade(true).build()
    )
    val iconResId =
        if (musicControllerUiState.playerState == PlayerState.PLAYING) R.drawable.ic_player_pause else R.drawable.ic_player_play


    Box(modifier = Modifier.fillMaxSize()) {
        PlayerScreenContent(
            song = song,
            isSongPlaying = musicControllerUiState.playerState == PlayerState.PLAYING,
            imagePainter = imagePainter,
            dominantColor = Color.White,
            currentTime = musicControllerUiState.currentPosition,
            totalTime = musicControllerUiState.totalDuration,
            playPauseIcon = iconResId,
            musicControllerUiState = musicControllerUiState,
            playOrToggleSong = {
                onEvent(if (musicControllerUiState.playerState == PlayerState.PLAYING) SongEvent.PauseSong else SongEvent.ResumeSong)
            },
            playNextSong = {
                onEvent(SongEvent.SkipToNextSong)
            },
            playPreviousSong = {
                onEvent(SongEvent.SkipToPreviousSong)
            },
            onSliderChange = { newPosition ->
                onEvent(SongEvent.SeekSongToPosition(newPosition.toLong()))
            },
            onClose = {
                onNavigateUp()
            }

        )

        // Add SoundBarsVisualizer
//        SoundBarsVisualizer(
//            player = exoPlayer,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp)
//                .align(Alignment.BottomCenter)
//                .padding(16.dp)
//        )
    }
}

//@Composable
//fun PlayerScreenBody(
//    song: Song,
//    onEvent: (SongEvent) -> Unit,
//    musicControllerUiState: MusicControllerUiState,
//    onNavigateUp: () -> Unit,
//) {
//    val context = LocalContext.current
//
//    val imagePainter = rememberAsyncImagePainter(
//        model = ImageRequest.Builder(context).data(song.albumArtUri).crossfade(true).build()
//    )
//    val iconResId =
//        if (musicControllerUiState.playerState == PlayerState.PLAYING) R.drawable.ic_player_pause else R.drawable.ic_player_play
//
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        PlayerScreenContent(
//            song = song,
//            isSongPlaying = musicControllerUiState.playerState == PlayerState.PLAYING,
//            imagePainter = imagePainter,
//            dominantColor = Color.White,
//            currentTime = musicControllerUiState.currentPosition,
//            totalTime = musicControllerUiState.totalDuration,
//            playPauseIcon = iconResId,
//            playOrToggleSong = {
//                onEvent(if (musicControllerUiState.playerState == PlayerState.PLAYING) SongEvent.PauseSong else SongEvent.ResumeSong)
//            },
//            playNextSong = {
//                onEvent(SongEvent.SkipToNextSong)
//            },
//            playPreviousSong = {
//                onEvent(SongEvent.SkipToPreviousSong)
//            },
//            onSliderChange = { newPosition ->
//                onEvent(SongEvent.SeekSongToPosition(newPosition.toLong()))
//            },
//            onClose = {
//                onNavigateUp()
//            }
//
//        )
//    }
//
//}

@Composable
fun PlayerScreenContent(
    song: Song,
    isSongPlaying: Boolean,
    imagePainter: Painter,
    dominantColor: Color,
    musicControllerUiState: MusicControllerUiState,
    currentTime: Long,
    totalTime: Long,
    @DrawableRes playPauseIcon: Int,
    playOrToggleSong: () -> Unit,
    playNextSong: () -> Unit,
    playPreviousSong: () -> Unit,
    onSliderChange: (Float) -> Unit,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = imagePainter,
            contentDescription = "",
            modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp / 1.6f),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                Color(0f, 0f, 0f, 0.5f),
                blendMode = BlendMode.Darken
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onClose() },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0x9A000000)),
                    tint = Color.White
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0x00000000),
                            Color(0x1A000000),
                            Color(0x33000000),
                            Color(0x66000000),
                            Color(0x99000000),
                            Color(0xCC000000),
                            Color(0xFF000000),
                        ),
                        startY = 0f,
                        endY = 300f,
                    )
                )
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = song.title,
                color = Color.White,
                maxLines = 2,
                fontFamily = bodyFontFamily,
                overflow = TextOverflow.Ellipsis,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
            Text(
                text = song.title,
                color = Color.White,
                maxLines = 1,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 120.dp)
                    .graphicsLayer {
                        alpha = 0.6f
                    },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clickable {
                        playOrToggleSong()
                    },
                painter = painterResource(id = playPauseIcon),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "${currentTime.toTime()}/${totalTime.toTime()}", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_shuffle),
                    tint = Color.Gray,
                    contentDescription = ""
                )
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .border(BorderStroke(0.5.dp, Color.Gray), CircleShape)
                        .padding(14.dp)
                        .clickable {
                            playPreviousSong()
                        },
                    painter = painterResource(id = R.drawable.ic_prev_song),
                    tint = Color.White,
                    contentDescription = ""
                )
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .border(BorderStroke(0.5.dp, Color.Gray), CircleShape)
                        .padding(14.dp)
                        .clickable {
                            playNextSong()
                        },
                    painter = painterResource(id = R.drawable.ic_next_song),
                    tint = Color.White,
                    contentDescription = ""
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_loop_repeate),
                    tint = Color.Gray,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Slider(
                    value = currentTime.toFloat(),
                    onValueChange = onSliderChange,
                    modifier = Modifier.fillMaxWidth(),
                    valueRange = 0f..totalTime.toFloat(),
                )

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Sound bar with beat syncing",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@OptIn(UnstableApi::class)
@Composable
fun SoundBarsVisualizer(player: ExoPlayer, modifier: Modifier = Modifier) {
    // State to hold the waveform data
    val waveformData = remember { mutableStateOf(List(20) { 0 }) }

    LaunchedEffect(player) {
        val audioSessionId = player.audioSessionId
        if (audioSessionId != C.AUDIO_SESSION_ID_UNSET) {
            try {
                val visualizer = Visualizer(audioSessionId).apply {
                    captureSize = Visualizer.getCaptureSizeRange()[1]
                    setDataCaptureListener(
                        object : Visualizer.OnDataCaptureListener {
                            override fun onWaveFormDataCapture(
                                visualizer: Visualizer,
                                waveform: ByteArray,
                                samplingRate: Int
                            ) {
                                // Divide the waveform into 20 segments
                                val segmentSize = waveform.size / 20
                                val heights = (0 until 20).map { i ->
                                    val segment =
                                        waveform.slice(i * segmentSize until (i + 1) * segmentSize)
                                    segment.map { byte -> (byte.toInt() + 128) * 100 / 256 }
                                        .average().toInt()
                                }
                                waveformData.value = heights
                            }

                            override fun onFftDataCapture(
                                visualizer: Visualizer,
                                fft: ByteArray,
                                samplingRate: Int
                            ) {
                                // Optional: Handle FFT data if needed
                            }
                        },
                        Visualizer.getMaxCaptureRate() / 2,
                        true,
                        false
                    )
                    enabled = true
                }

//                onDispose {
//                    visualizer.release()
//                }
            } catch (e: RuntimeException) {
                Log.e("Visualizer", "Visualizer initialization failed", e)
            }
        }
    }

    // Draw the 20 sound bars using the processed waveform data
    Row(
        modifier = modifier
            .fillMaxWidth()
            .rotate(180f)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        waveformData.value.forEach { height ->
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(height.dp)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }
    }
}
