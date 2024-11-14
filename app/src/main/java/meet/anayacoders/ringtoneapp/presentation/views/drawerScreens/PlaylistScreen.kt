package meet.anayacoders.ringtoneapp.presentation.views.drawerScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import meet.anayacoders.ringtoneapp.ui.theme.msdLabelViewAll
import meet.anayacoders.ringtoneapp.ui.theme.msdSongSingerName
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.data.local.entity.PlaylistEntity
import meet.anayacoders.ringtoneapp.ui.playlist.PlaylistViewModel

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier, viewModel: PlaylistViewModel) {
    val playlists by viewModel.playlists.collectAsState()
    var showCreatePlaylistDialog by remember { mutableStateOf(false) }
    var showDeletePlaylistDialog by remember { mutableStateOf(false) }
    var selectedPlaylist by remember {
        mutableStateOf(PlaylistEntity())
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showCreatePlaylistDialog = true

            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) { pd ->
        println(pd)

        if (showCreatePlaylistDialog) {
            AddPlaylistDialog(onDismiss = { showCreatePlaylistDialog = false }, onCreate = {
                viewModel.addPlaylist(PlaylistEntity(name = it))
            })
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 8.dp)
        ) {
            items(playlists) {
                PlaylistItem(playlistEntity = it) { isOpen, playlist ->
                    showDeletePlaylistDialog = isOpen
                    selectedPlaylist = playlist
                }
            }
        }
        if (showDeletePlaylistDialog) {
            AlertDialog(
                title = {
                    Text(text = "Delete Playlist!")
                },
                text = {
                    Text(text = "Are you sure want to delete playlist?")
                },
                onDismissRequest = { showDeletePlaylistDialog = false },
                confirmButton = {
                    Button(onClick = {
                        showDeletePlaylistDialog = false
                        viewModel.deletePlaylist(selectedPlaylist)
                    }) {
                        Text(text = "Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeletePlaylistDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaylistItem(
    modifier: Modifier = Modifier,
    playlistEntity: PlaylistEntity,
    onLongClick: (Boolean, PlaylistEntity) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = {
                    onLongClick(true, playlistEntity)
                },
                onClick = {

                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(56.dp)
                .background(Color.Gray)
                .padding(16.dp),
            painter = painterResource(id = R.drawable.ic_playlist_thumbnail),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = playlistEntity.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = msdLabelViewAll
            )
            Text(
                text = "${playlistEntity.songs.size} songs",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = msdSongSingerName
            )
        }
        Image(
            modifier = Modifier
                .size(40.dp)
                .padding(12.dp)
                .clickable {
                    //TODO: open menu for deleting a playlist
                },
            colorFilter = ColorFilter.tint(Color.Gray),
            painter = painterResource(id = R.drawable.ic_dots_menu),
            contentDescription = ""
        )
    }
}


@Composable
fun AddPlaylistDialog(
    onDismiss: () -> Unit,
    onCreate: (String) -> Unit
) {
    var playlistName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss },
        title = {
            Text(text = "Create New Playlist")
        },
        text = {
            TextField(value = playlistName, onValueChange = { playlistName = it })
        },
        confirmButton = {
            TextButton(onClick = {
                onCreate(playlistName)
                onDismiss()
            }
            ) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel", color = MaterialTheme.colorScheme.onErrorContainer)
            }
        }
    )
}