package meet.anayacoders.ringtoneapp.ui.routes

//
//@Serializable
//object Home
//
//@Serializable
//object Playlist
//
//@Serializable
//object Player
//
//@Serializable
//object Artists
//
//@Serializable
//object Album
//
//@Serializable
//object Downloads
//
//@Serializable
//object Settings

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Player : Screen("player")
    object Playlist : Screen("playlist")
    object Artists : Screen("artists")
    object Album : Screen("album")
    object CategorizedSong : Screen("downloads")
//    object Settings : Screen("settings")

}