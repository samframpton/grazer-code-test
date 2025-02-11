package sam.frampton.grazer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import sam.frampton.grazer.ui.navigation.GrazerNavHost
import sam.frampton.grazer.ui.theme.GrazerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrazerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GrazerNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
