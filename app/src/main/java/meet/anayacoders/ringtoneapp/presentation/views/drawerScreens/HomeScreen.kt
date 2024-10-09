package meet.anayacoders.ringtoneapp.presentation.views.drawerScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.compose.albumHeadingGreen
import com.example.compose.gradient1End
import com.example.compose.gradient1start
import com.example.compose.labelViewAllGrayColor
import com.example.ui.theme.msdAlbumName
import com.example.ui.theme.msdAlbumOwnerName
import com.example.ui.theme.msdLabelHeading
import com.example.ui.theme.msdLabelViewAll
import com.example.ui.theme.msdNewAlbumLabel
import com.example.ui.theme.msdSongSingerName
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.presentation.model.AlbumHomeModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column {
        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {

            val albumList = listOf(
                AlbumHomeModel(
                    albumName = "Breath Unearth",
                    albumImage = "https://images.pexels.com/photos/4545333/pexels-photo-4545333.jpeg?auto=compress&cs=tinysrgb&w=600.",
                    albumOwnerName = "Decy sookie",
                    albumOwnerImg = "https://images.pexels.com/photos/1043473/pexels-photo-1043473.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
                AlbumHomeModel(
                    albumName = "Calm Down",
                    albumImage = "https://images.pexels.com/photos/6670752/pexels-photo-6670752.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    albumOwnerName = "Monalisa pie",
                    albumOwnerImg = "https://images.pexels.com/photos/775358/pexels-photo-775358.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
                AlbumHomeModel(
                    albumName = "Unforgettable town",
                    albumImage = "https://images.pexels.com/photos/2114758/pexels-photo-2114758.jpeg?auto=compress&cs=tinysrgb&w=600",
                    albumOwnerName = "Gemmy dies",
                    albumOwnerImg = "https://images.pexels.com/photos/1680172/pexels-photo-1680172.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                ),
            )

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

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp),

                ) {
                //            albumList.forEach { item ->
                SongItemHomeScreen()
                SongItemHomeScreen()
                SongItemHomeScreen()
                SongItemHomeScreen()
                SongItemHomeScreen()
                SongItemHomeScreen()
                SongItemHomeScreen()
                //            }
            }
        }
        SongSnackBar()
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
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Album Name",
                    style = msdAlbumName,
                    color = Color.White
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
                        text = "Album Author",
                        color = Color.White,
                        style = msdAlbumOwnerName
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
            modifier = Modifier
                .fillMaxSize()

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
        ) {
        }
        Text(
            text = "Pop",
            textAlign = TextAlign.End,
            color = Color.White,
            style = msdLabelViewAll,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
fun SongItemHomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(72.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.demo), contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Ve kamaleya - Rockey ki prem Kahaani",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = msdLabelViewAll
                )
                Text(
                    text = "Arijit Singh & Shreya Ghoshal",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = msdSongSingerName
                )
            }
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.ic_play_btn),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun SongSnackBar(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF000000))
            .padding(bottom = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(72.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.demo), contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Ve kamaleya - Rockey ki prem Kahaani",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = msdLabelViewAll
                )
                Text(
                    text = "Arijit Singh & Shreya Ghoshal",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = msdSongSingerName
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                    modifier = Modifier.padding(end = 24.dp)
            ) {
//                    Image(
//                        modifier = Modifier.size(16.dp),
//                        painter = painterResource(id = R.drawable.ic_prev_song),
//                        colorFilter = ColorFilter.tint(
//                            Color.White
//                        ),
//                        contentDescription = ""
//                    )
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.ic_pause_btn),
                    contentDescription = ""
                )
                Image(
                    modifier = Modifier.size(22.dp),
                    colorFilter = ColorFilter.tint(
                        Color.White
                    ),
                    painter = painterResource(id = R.drawable.ic_next_song),
                    contentDescription = ""
                )
            }
        }
    }
}