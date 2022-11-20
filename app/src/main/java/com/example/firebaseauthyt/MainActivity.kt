package com.example.firebaseauthyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.firebaseauthyt.navigation.NavigationGraph
import com.example.firebaseauthyt.ui.theme.FirebaseAuthYTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthYTTheme {
                NavigationGraph()
            }
        }
    }
}

