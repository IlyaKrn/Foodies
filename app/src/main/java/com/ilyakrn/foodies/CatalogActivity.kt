package com.ilyakrn.foodies

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ilyakrn.foodies.ui.screens.CatalogScreen
import com.ilyakrn.foodies.ui.screens.SplashScreen
import com.ilyakrn.foodies.ui.theme.FoodiesTheme

class CatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodiesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CatalogScreen(
                        onShowBasket = {
                            val intent = Intent(this@CatalogActivity, BasketActivity::class.java)
                            startActivity(intent)
                        },
                        onShowProductInfo = {
                            val intent = Intent(this@CatalogActivity, ProductInfoActivity::class.java)
                            intent.putExtra("id", it)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}