package com.ilyakrn.foodies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ilyakrn.foodies.ui.screens.BasketScreen
import com.ilyakrn.foodies.ui.screens.CatalogScreen
import com.ilyakrn.foodies.ui.screens.ProductInfoScreen
import com.ilyakrn.foodies.ui.screens.SplashScreen
import com.ilyakrn.foodies.ui.theme.FoodiesTheme
import java.lang.Exception

@SuppressLint("CustomSplashScreen")
class MainActivity : ComponentActivity() {

    //экраны навигации
    enum class Screens{
        Catalog,
        ProductInfo,
        Basket
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodiesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val controller = NavHostController(this@MainActivity)
                    controller.navigatorProvider.addNavigator(ComposeNavigator())
                    controller.navigatorProvider.addNavigator(DialogNavigator())
                    val mutableNext = remember {
                        mutableStateOf(false)
                    }
                    Thread{
                        Thread.sleep(2000)
                        mutableNext.value = true
                    }.start()
                    if(mutableNext.value) {
                        NavHost(
                            modifier = Modifier.fillMaxSize(),
                            navController = controller,
                            startDestination = Screens.Catalog.name,
                            enterTransition = {
                                EnterTransition.None
                            },
                            exitTransition = {
                                ExitTransition.None
                            }) {
                            composable(route = Screens.Catalog.name) {
                                CatalogScreen(
                                    onShowBasket = {
                                        controller.navigate(Screens.Basket.name)
                                    },
                                    onShowProductInfo = {
                                        controller.currentBackStackEntry?.savedStateHandle?.set("id", it)
                                        controller.navigate(Screens.ProductInfo.name)
                                    }
                                )
                            }
                            composable(route = Screens.Basket.name) {
                                BasketScreen(
                                    onClose = {
                                        controller.navigateUp()
                                    },
                                    onShowProductInfo = {
                                        controller.currentBackStackEntry?.savedStateHandle?.set("id", it)
                                        controller.navigate(Screens.ProductInfo.name)
                                    }
                                )
                            }
                            composable(route = Screens.ProductInfo.name) {
                                var id = -1L
                                if(controller.previousBackStackEntry != null && controller.previousBackStackEntry!!.savedStateHandle.get<Long?>("id") != null)
                                    id = controller.previousBackStackEntry!!.savedStateHandle["id"]!!
                                ProductInfoScreen(
                                    id = id,
                                    onClose = {
                                        controller.navigateUp()
                                    }
                                )
                            }
                        }
                    }
                    else{
                        SplashScreen()
                    }
                }
            }
        }
    }
}