package meet.anayacoders.ringtoneapp.ui.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItem(
    val title : String,
    val selectedIcon : Painter,
    val unselectedIcon : Painter,
    val badgeCount : Int? = null,
    val route : String? = null
)
