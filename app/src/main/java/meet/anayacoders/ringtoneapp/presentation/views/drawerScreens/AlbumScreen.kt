package meet.anayacoders.ringtoneapp.presentation.views.drawerScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.msdAlbumSongName
import meet.anayacoders.ringtoneapp.R

@Composable
@Preview
fun AlbumScreen(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(10) {
            AlbumItem()
        }
    }
}

@Composable
fun AlbumItem(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.demo), contentDescription = ""
        )
        Text(
            text = "Album name aef aue gf aeugf irufha eirh aeiruh sdfg dfg a eri",
            maxLines = 2,
            color = Color.White,
            style = msdAlbumSongName,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Unknown Artist",
                color = Color.Gray,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                style = msdAlbumSongName,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "1:46:56",
                color = Color.Gray,
                style = msdAlbumSongName
            )
        }

    }
}