package meet.anayacoders.ringtoneapp.ui

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch
import meet.anayacoders.ringtoneapp.R
import meet.anayacoders.ringtoneapp.ui.album.AlbumScreen
import meet.anayacoders.ringtoneapp.ui.artist.ArtistsScreen
import meet.anayacoders.ringtoneapp.ui.home.HomeEvent
import meet.anayacoders.ringtoneapp.ui.home.HomeScreen
import meet.anayacoders.ringtoneapp.ui.home.HomeViewModel
import meet.anayacoders.ringtoneapp.ui.home.component.HomeBottomBar
import meet.anayacoders.ringtoneapp.ui.model.NavigationDrawerItem
import meet.anayacoders.ringtoneapp.ui.playerscreen.PlayerScreen
import meet.anayacoders.ringtoneapp.ui.playerscreen.SongViewModel
import meet.anayacoders.ringtoneapp.ui.playlist.PlaylistScreen
import meet.anayacoders.ringtoneapp.ui.playlist.PlaylistViewModel
import meet.anayacoders.ringtoneapp.ui.routes.Screen
import meet.anayacoders.ringtoneapp.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    val currentRoute = remember { mutableStateOf("Home") } // Default route is 'home'

    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute.value = destination.route ?: "Home"
    }

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

    )

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val currentDestination = navController.currentBackStackEntryAsState()

        val selectedItemIndex by remember {
            derivedStateOf {
                when (currentDestination.value?.destination?.route) {
                    Screen.Home.route -> drawerItemsLibrary.indexOfFirst { it.title == "Home" }
                    Screen.Artists.route -> drawerItemsLibrary.indexOfFirst { it.title == "Artists" }
                    Screen.Playlist.route -> drawerItemsLibrary.indexOfFirst { it.title == "Playlist" }
                    Screen.Album.route -> drawerItemsLibrary.indexOfFirst { it.title == "Album" }
                    else -> drawerItemsLibrary.indexOfFirst { it.title == "Home" }
                }
            }
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
                                when (item.title) {
                                    "Home" -> {
                                        navController.navigate(Screen.Home.route){
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }

                                    "Playlist" -> {
                                        navController.navigate(Screen.Playlist.route){
                                            popUpTo(Screen.Home.route) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }

                                    "Artists" -> {
                                        navController.navigate(Screen.Artists.route){
                                            popUpTo(Screen.Home.route) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }

                                    "Album" -> {
                                        navController.navigate(Screen.Album.route){
                                            popUpTo(Screen.Home.route) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }

                                    else -> {
                                        navController.navigate(Screen.Home.route){
                                            popUpTo(Screen.Home.route) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
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
                if (currentRoute.value != Screen.Player.route) {
                    TopAppBar(title = { Text("Music Player") }, navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "")
                        }
                    })
                }
            }) { pd ->

                val homeViewModel: HomeViewModel = hiltViewModel()
                val isInitialized = rememberSaveable { mutableStateOf(false) }

                if (!isInitialized.value) {
                    LaunchedEffect(key1 = Unit) {
                        homeViewModel.onEvent(HomeEvent.FetchAllSong)
                        isInitialized.value = true
                    }
                }


                NavHost(navController = navController, startDestination = Screen.Home.route) {

                    composable(Screen.Home.route) {
//                        val homeViewModel: HomeViewModel = hiltViewModel()
//                        val isInitialized = rememberSaveable { mutableStateOf(false) }

                        if (!isInitialized.value) {
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.onEvent(HomeEvent.FetchAllSong)
                                isInitialized.value = true
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(pd),
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
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
                                onBarClick = { navController.navigate(Screen.Player.route) }
                            )
                        }

                    }
                    composable(Screen.Playlist.route) {

                        val playlistViewModel: PlaylistViewModel = hiltViewModel()

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(pd),
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                PlaylistScreen(
                                    modifier = Modifier,
                                    viewModel = playlistViewModel
                                )
                            }

                            HomeBottomBar(
                                onEvent = homeViewModel::onEvent,
                                song = musicControllerUiState.currentSong,
                                playerState = musicControllerUiState.playerState,
                                onBarClick = { navController.navigate(Screen.Player.route) }
                            )
                        }




                    }
                    composable(Screen.Player.route) {

                        val songViewModel: SongViewModel = hiltViewModel()


                        PlayerScreen(
                            modifier = Modifier.padding(pd),
                            onEvent = songViewModel::onEvent,
                            musicControllerUiState = musicControllerUiState,
                            onNavigateUp = {
                                navController.navigateUp()
                            },
                        )
                    }
                    composable(Screen.Artists.route) {
//                        val homeViewModel: HomeViewModel = hiltViewModel()
//                        val isInitialized = rememberSaveable { mutableStateOf(false) }
//
                        if (!isInitialized.value) {
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.onEvent(HomeEvent.FetchAllSong)
                                isInitialized.value = true
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(pd),
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                ArtistsScreen(
                                    onEvent = homeViewModel::onEvent,
                                    uiState = homeViewModel.homeUiState
                                ){
                                    navController.navigate(Screen.CategorizedSong.route)
                                }
                            }

                            HomeBottomBar(
                                onEvent = homeViewModel::onEvent,
                                song = musicControllerUiState.currentSong,
                                playerState = musicControllerUiState.playerState,
                                onBarClick = { navController.navigate(Screen.Player.route) }
                            )
                        }


                    }
                    composable(Screen.Album.route){
//                        val homeViewModel: HomeViewModel = hiltViewModel()
//                        val isInitialized = rememberSaveable { mutableStateOf(false) }
//
                        if (!isInitialized.value) {
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.onEvent(HomeEvent.FetchAllSong)
                                isInitialized.value = true
                            }
                        }
//
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(pd),
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {

                                AlbumScreen(
                                    onEvent = homeViewModel::onEvent,
                                    uiState = homeViewModel.homeUiState
                                ){
                                    navController.navigate(Screen.CategorizedSong.route)
                                }
                            }

                            HomeBottomBar(
                                onEvent = homeViewModel::onEvent,
                                song = musicControllerUiState.currentSong,
                                playerState = musicControllerUiState.playerState,
                                onBarClick = { navController.navigate(Screen.Player.route) }
                            )
                        }

                    }
                    composable(Screen.CategorizedSong.route){

                        if (!isInitialized.value) {
                            LaunchedEffect(key1 = Unit) {
                                homeViewModel.onEvent(HomeEvent.FetchAllSong)
                                isInitialized.value = true
                            }
                        }
//
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(pd),
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {

                                CategorizedSongScreen(
                                    onEvent = homeViewModel::onEvent,
                                    uiState = homeViewModel.homeUiState
                                )

                            }

                            HomeBottomBar(
                                onEvent = homeViewModel::onEvent,
                                song = musicControllerUiState.currentSong,
                                playerState = musicControllerUiState.playerState,
                                onBarClick = { navController.navigate(Screen.Player.route) }
                            )
                        }
                    }

                }
            }
        }
    }
}