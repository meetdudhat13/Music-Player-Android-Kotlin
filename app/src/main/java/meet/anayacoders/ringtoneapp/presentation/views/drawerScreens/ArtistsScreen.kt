package meet.anayacoders.ringtoneapp.presentation.views.drawerScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.ui.theme.msdLabelViewAll
import com.example.ui.theme.msdSongSingerName
import meet.anayacoders.ringtoneapp.R

@Composable
fun ArtistsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
//            .background(Color.Blue)
            .padding(vertical = 8.dp)
    ) {
        ArtistItem()
        ArtistItem()
        ArtistItem()
        ArtistItem()
        ArtistItem()
        ArtistItem()
    }
}


@Composable
fun ArtistItem(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
//            .background(Color.Red),
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
                text = "Arijit singh",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = msdLabelViewAll
            )
            Text(
                text = "5 songs",
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