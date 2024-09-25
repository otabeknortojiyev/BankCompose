package uz.gita.otabek.bankauthcompose.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.otabek.bankauthcompose.screens.splash.SplashScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.NavigationHandler
import uz.gita.otabek.bankauthcompose.ui.theme.BankAuthComposeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContent {
            BankAuthComposeTheme {
                Navigator(screen = SplashScreen, onBackPressed = {
                    true
                }) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigationHandler.screenState.onEach {
                            it.invoke(navigator)
                        }.launchIn(lifecycleScope)
                    }
                    SlideTransition(navigator = navigator)
                }
            }
        }
    }
}

