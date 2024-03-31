package com.ilyakrn.foodies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.ilyakrn.foodies.ui.screens.CatalogScreen
import com.ilyakrn.foodies.ui.screens.SplashScreen
import com.ilyakrn.foodies.ui.theme.FoodiesTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen()
            Thread{
                Thread.sleep(3000)
                val intent = Intent(this@SplashActivity, CatalogActivity::class.java)
                startActivity(intent)
                finish()
            }.start()
        }
    }
}