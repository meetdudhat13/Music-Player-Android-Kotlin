package meet.anayacoders.ringtoneapp.ui.playerscreen

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
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

    }
}


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
            painter = if (song.albumArtUri!="") imagePainter else painterResource(id = R.drawable.demo),
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
            Text(text = "${currentTime.toTime()} / ${totalTime.toTime()}", color = Color.White)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    valueRange = 0f..totalTime.toFloat(),
                )

                Spacer(modifier = Modifier.height(32.dp))

                AnimatedVisibility(
                    visible = isSongPlaying,
                    enter = fadeIn(animationSpec = tween(500)),
                    exit = fadeOut(animationSpec = tween(500))
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        MovingWave(frequency = 24, waveColor = Color.White)
                        MovingWave(frequency = 24, initialOffset = 3f, waveColor = Color.Cyan)
                    }
                }
            }
        }
    }

}


@Composable
fun MovingWave(
    waveColor: Color = Color.Blue,       // Wave color
    speed: Int = 1600,                  // Speed of one cycle (in milliseconds)
    thickness: Float = 5f,              // Thickness of the wave line
    frequency: Int = 12,                // Number of oscillations across the width
    amplitude: Float = 100f,            // Amplitude (height) of the wave
    initialOffset: Float = 0f           // Initial phase offset for the wave
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = initialOffset, // Start from the given initial offset
        targetValue = initialOffset + 2f * Math.PI.toFloat(), // Complete one full sine wave cycle
        animationSpec = infiniteRepeatable(
            animation = tween(speed, easing = { it }) // Linear easing for smooth motion
        ),
        label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val waveWidth = size.width
        val waveHeight = size.height / 2

        val wavePath = Path().apply {
            var x = 0f
            val startY =
                waveHeight + amplitude * kotlin.math.sin(animatedOffset) // Calculate the initial Y position
            moveTo(x, startY)

            x += 10f
            while (x <= waveWidth) {
                val y =
                    waveHeight + amplitude * kotlin.math.sin(frequency * x / waveWidth + animatedOffset)
                lineTo(x, y)
                x += 10f
            }
        }

        drawPath(
            path = wavePath,
            color = waveColor,
            style = Stroke(width = thickness)
        )
    }
}