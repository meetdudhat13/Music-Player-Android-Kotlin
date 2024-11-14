package meet.anayacoders.ringtoneapp.presentation.views.drawerScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.presentation.model.NavigationDrawerItem
import meet.anayacoders.ringtoneapp.presentation.routes.Album
import meet.anayacoders.ringtoneapp.presentation.routes.Artists
import meet.anayacoders.ringtoneapp.presentation.routes.Downloads
import meet.anayacoders.ringtoneapp.presentation.routes.Home
import meet.anayacoders.ringtoneapp.presentation.routes.Player
import meet.anayacoders.ringtoneapp.presentation.routes.Playlist
import meet.anayacoders.ringtoneapp.presentation.routes.Settings
import meet.anayacoders.ringtoneapp.ui.home.HomeEvent
import meet.anayacoders.ringtoneapp.ui.home.HomeScreen
import meet.anayacoders.ringtoneapp.ui.home.HomeViewModel
import meet.anayacoders.ringtoneapp.ui.home.component.HomeBottomBar
import meet.anayacoders.ringtoneapp.ui.playerscreen.PlayerScreen
import meet.anayacoders.ringtoneapp.ui.playerscreen.SongEvent
import meet.anayacoders.ringtoneapp.ui.playerscreen.SongViewModel
import meet.anayacoders.ringtoneapp.ui.playlist.PlaylistViewModel
import meet.anayacoders.ringtoneapp.ui.viewmodel.SharedViewModel
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    val drawerItemsLibrary = listOf(
        NavigationDrawerItem(
            title = "Home",
            selectedIcon = painterResource(id = R.drawable.ic_home),
            unselectedIcon = painterResource(id = R.drawable.ic_home_outlined)
        ),
        NavigationDrawerItem(
            title = "Playlist",
            selectedIcon = painterResource(id = R.drawable.ic_playlist),
            unselectedIcon = painterResource(id = R.drawable.ic_playlist_outlined)
        ),
//        NavigationDrawerItem(
//            title = "Genres",
//            selectedIcon = painterResource(id = R.drawable.ic_genres),
//            unselectedIcon = painterResource(id = R.drawable.ic_genres_outlined)
//        ),
        NavigationDrawerItem(
            title = "Artists",
            selectedIcon = painterResource(id = R.drawable.ic_artist),
            unselectedIcon = painterResource(id = R.drawable.ic_artist_outlined)
        ),
        NavigationDrawerItem(
            title = "Album",
            selectedIcon = painterResource(id = R.drawable.ic_album),
            unselectedIcon = painterResource(id = R.drawable.ic_album_outlined)
        ),
        NavigationDrawerItem(
            title = "Downloads",
            selectedIcon = painterResource(id = R.drawable.ic_download),
            unselectedIcon = painterResource(id = R.drawable.ic_download)
        ),
        NavigationDrawerItem(
            title = "Settings",
            selectedIcon = painterResource(id = R.drawable.ic_settings),
            unselectedIcon = painterResource(id = R.drawable.ic_settings_outlined)
        ),
        NavigationDrawerItem(
            title = "Help & Feedback",
            selectedIcon = painterResource(id = R.drawable.ic_help),
            unselectedIcon = painterResource(id = R.drawable.ic_help_outlined)
        ),
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        val musicControllerUiState = sharedViewModel.musicControllerUiState

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Image(
                        painter = painterResource(id = R.drawable.demo),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.height(200.dp)
                    )
//                                Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Library",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
//                                Spacer(modifier = Modifier.height(40.dp))
                    drawerItemsLibrary.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = { Text(text = item.title) },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                when (item.title) {
                                    "Home" -> {
                                        navController.navigate(Home)
                                    }

                                    "Playlist" -> {
                                        navController.navigate(Playlist)
                                    }
//                                    "Genres" -> {
//                                        navController.navigate(Genres)
//                                    }

                                    "Artists" -> {
                                        navController.navigate(Artists)
                                    }

                                    "Album" -> {
                                        navController.navigate(Album)
                                    }

                                    "Downloads" -> {
                                        navController.navigate(Downloads)
                                    }

                                    "Settings" -> {
                                        navController.navigate(Settings)
                                    }

                                    else -> {
                                        navController.navigate(Home)
                                    }
                                }
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    painter = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            badge = {
                                item.badgeCount?.let {
                                    Text(text = item.badgeCount.toString())

                                }
                            },
                            modifier = Modifier.padding(
                                NavigationDrawerItemDefaults.ItemPadding
                            )
                        )
                    }
                }
            },
            drawerState = drawerState,
        ) {
            Scaffold(topBar = {
                TopAppBar(title = { Text("Music Player") }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "")
                    }
                })
            }) { pd ->
                NavHost(navController = navController, startDestination = Home) {
                    composable<Home> {
                        val homeViewModel: HomeViewModel = hiltViewModel()
                        val isInitialized = rememberSaveable { mutableStateOf(false) }

                        if (!isInitialized.value) {
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.onEvent(HomeEvent.FetchSong)
                                isInitialized.value = true
                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxSize().padding(pd),
                        ) {
                            Box(
                                modifier = Modifier.weight(1f).fillMaxWidth()
                            ) {
                                HomeScreen(
                                    onEvent = homeViewModel::onEvent,
                                    uiState = homeViewModel.homeUiState,
                                    modifier = Modifier
                                )
                            }

                            HomeBottomBar(
                                onEvent = homeViewModel::onEvent,
                                song = musicControllerUiState.currentSong,
                                playerState = musicControllerUiState.playerState,
                                onBarClick = { navController.navigate(Player) }
                            )
                        }

                    }
                    composable<Playlist> {

                        val playlistViewModel: PlaylistViewModel = hiltViewModel()
                        PlaylistScreen(modifier = Modifier.padding(pd), viewModel = playlistViewModel)

                    }
                    composable<Player> {

                        val songViewModel: SongViewModel = hiltViewModel()


                        PlayerScreen(modifier = Modifier.padding(pd),
                            onEvent = songViewModel::onEvent,
                            musicControllerUiState = musicControllerUiState,
                            onNavigateUp = {
                                navController.navigateUp()
                            },
                        )
                    }
                    composable<Artists> {
                        val homeViewModel: HomeViewModel = hiltViewModel()
                        val isInitialized = rememberSaveable { mutableStateOf(false) }

                        if (!isInitialized.value) {
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.onEvent(HomeEvent.FetchSong)
                                isInitialized.value = true
                            }
                        }
                        ArtistsScreen(modifier = Modifier.padding(pd),
                            onEvent = homeViewModel::onEvent,
                            uiState = homeViewModel.homeUiState
                        )
                    }
                    composable<Album> {
                        val homeViewModel: HomeViewModel = hiltViewModel()
                        val isInitialized = rememberSaveable { mutableStateOf(false) }

                        if (!isInitialized.value) {
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.onEvent(HomeEvent.FetchSong)
                                isInitialized.value = true
                            }
                        }
                        AlbumScreen(modifier = Modifier.padding(pd),
                            onEvent = homeViewModel::onEvent,
                            uiState = homeViewModel.homeUiState)
                    }
                    composable<Downloads> {
                        DownloadScreen(modifier = Modifier.padding(pd))
                    }
                    composable<Settings> {
                        SettingScreen(modifier = Modifier.padding(pd))
                    }

                }

            }
        }
    }
}