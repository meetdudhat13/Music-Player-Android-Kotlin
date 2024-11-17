package meet.anayacoders.ringtoneapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import meet.anayacoders.ringtoneapp.data.service.MusicService
import meet.anayacoders.ringtoneapp.ui.MainScreen
import meet.anayacoders.ringtoneapp.ui.viewmodel.SharedViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()

                MainScreen(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        sharedViewModel.destroyMediaController()
        stopService(Intent(this, MusicService::class.java))
    }

}
