package uz.gita.otabek.bankauthcompose.screens.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.utils.Lang
import uz.gita.otabek.bankauthcompose.utils.setLanguage
import uz.gita.otabek.presenter.splash.SplashContract
import uz.gita.otabek.presenter.splash.SplashViewModel


object SplashScreen : Screen {
    private fun readResolve(): Any = SplashScreen

    @Composable
    override fun Content() {
        val viewModel: SplashContract.ViewModel = getViewModel<SplashViewModel>()
        val uiState = viewModel.collectAsState()
        SplashScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@SuppressLint("ResourceAsColor")
@Composable
private fun SplashScreenContent(
    uiState: State<SplashContract.UiState>,
    onEventDispatcher: (SplashContract.Intent) -> Unit
) {
    val context = LocalContext.current
    onEventDispatcher(SplashContract.Intent.CheckLanguage)
    when (uiState.value.language) {
        "uz" -> {
            setLanguage(language = Lang.uz, context)
        }

        "ru" -> {
            setLanguage(language = Lang.ru, context)
        }

        "en" -> {
            setLanguage(language = Lang.en, context)
        }
    }

    val scale = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(200)
        scale.animateTo(
            targetValue = 1f, animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )
        onEventDispatcher(SplashContract.Intent.MoveToPIN)
    }

    val letters = listOf("H", "A", "M", "K", "O", "R", "B", "A", "N", "K")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            letters.forEach {
                Text(
                    text = it, style = TextStyle(
                        color = MainGreen, fontSize = 36.sp, fontFamily = FontFamily(Font(R.font.archivo_black))
                    ), modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                        .scale(scale.value)
                )
            }
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
}