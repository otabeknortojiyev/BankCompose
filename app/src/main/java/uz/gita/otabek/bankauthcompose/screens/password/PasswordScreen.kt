package uz.gita.otabek.bankauthcompose.screens.password

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray

object PasswordScreen : Screen {
    private fun readResolve(): Any = PasswordScreen

    @Composable
    override fun Content() {
        val viewModel: PasswordContract.ViewModel = getViewModel<PasswordViewModel>()
        PasswordScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
    }
}

@Composable
private fun PasswordScreenContent(
    uiState: State<PasswordContract.UiState>, onEventDispatcher: (PasswordContract.Intent) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PasswordBackGroundGray)
    ) {
        val password = remember {
            mutableIntStateOf(0)
        }

        val pin = remember {
            mutableStateListOf<String>()
        }

        val guidLine05 = createGuidelineFromTop(0.05f)
        val guidLine15 = createGuidelineFromTop(0.15f)
        val guidLine25 = createGuidelineFromTop(0.25f)
        val guidLine3 = createGuidelineFromTop(0.3f)
        val guidLine9 = createGuidelineFromTop(0.9f)
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(createRef()) {
                    top.linkTo(guidLine05)
                }) {
            Image(
                painter = painterResource(id = R.drawable.hamkor_icon), contentDescription = null, modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
            )
            Text(
                text = "HAMKORBANK", style = TextStyle(
                    color = MainGreen, fontFamily = FontFamily(Font(R.font.archivo_black)), fontSize = 28.sp
                ), modifier = Modifier.padding(start = 4.dp)
            )
        }
        Text(text = if (uiState.value.error) {
            stringResource(id = R.string.password_screen_error_pin)
        } else {
            stringResource(id = R.string.password_screen_enter_pin)
        }, style = TextStyle(
            color = if (uiState.value.error) {
                Color.Red
            } else {
                Color.Gray
            }, fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.archivo_black))
        ), textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .constrainAs(createRef()) {
                top.linkTo(guidLine15)
            })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(createRef()) { top.linkTo(guidLine25) }, horizontalArrangement = Arrangement.Center
        ) {
            when (password.intValue) {
                0 -> {
                    PasswordRoundGray()
                    PasswordRoundGray()
                    PasswordRoundGray()
                    PasswordRoundGray()
                }

                1 -> {
                    PasswordRoundGreen()
                    PasswordRoundGray()
                    PasswordRoundGray()
                    PasswordRoundGray()
                }

                2 -> {
                    PasswordRoundGreen()
                    PasswordRoundGreen()
                    PasswordRoundGray()
                    PasswordRoundGray()
                }

                3 -> {
                    PasswordRoundGreen()
                    PasswordRoundGreen()
                    PasswordRoundGreen()
                    PasswordRoundGray()
                }

                4 -> {
                    if (uiState.value.error) {
                        PasswordRoundRed()
                        PasswordRoundRed()
                        PasswordRoundRed()
                        PasswordRoundRed()
                    } else {
                        PasswordRoundGreen()
                        PasswordRoundGreen()
                        PasswordRoundGreen()
                        PasswordRoundGreen()
                        LaunchedEffect(Unit) {
                            delay(500)
                            onEventDispatcher(PasswordContract.Intent.MoveToHome(pin[0] + pin[1] + pin[2] + pin[3]))
                        }
                    }
                }
            }
        }
        Column(modifier = Modifier
            .constrainAs(createRef()) {
                top.linkTo(guidLine3)
                bottom.linkTo(guidLine9)
            }
            .padding(start = 24.dp, end = 24.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PasswordScreenNumber(number = "1") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("1")
                    }
                }
                PasswordScreenNumber(number = "2") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("2")
                    }
                }
                PasswordScreenNumber(number = "3") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("3")
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PasswordScreenNumber(number = "4") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("4")
                    }
                }
                PasswordScreenNumber(number = "5") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("5")
                    }
                }
                PasswordScreenNumber(number = "6") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("6")
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PasswordScreenNumber(number = "7") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("7")
                    }
                }
                PasswordScreenNumber(number = "8") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("8")
                    }
                }
                PasswordScreenNumber(number = "9") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("9")
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PasswordScreenNumberTransparent()
                PasswordScreenNumber(number = "0") {
                    if (password.intValue != 4) {
                        password.intValue++
                        pin.add("0")
                    }
                }
                PasswordScreenNumberClear {
                    if (password.intValue != 0) {
                        password.intValue--
                        pin.removeAt(pin.size - 1)
                    }
                    if (password.intValue == 0 || password.intValue == 1 || password.intValue == 2 || password.intValue == 3) {
                        uiState.value.error = false
                    }
                }
            }
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(PasswordBackGroundGray)

}


@Composable
fun PasswordScreenNumber(number: String, clickable: () -> Unit) {
    Text(
        text = number,
        style = TextStyle(color = Color.Black, fontSize = 36.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular))),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .clickable { clickable() }
            .padding(24.dp)
            .width(36.dp)
            .height(36.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun PasswordScreenNumberTransparent() {
    Text(
        text = "",
        style = TextStyle(color = Color.Transparent, fontSize = 36.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular))),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .padding(24.dp)
            .width(36.dp)
            .height(36.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun PasswordScreenNumberClear(clickable: () -> Unit) {
    Image(painter = painterResource(id = R.drawable.clear),
        contentDescription = null,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable { clickable() }
            .padding(24.dp)
            .width(36.dp)
            .height(36.dp))
}

@Composable
fun PasswordRoundGray() {
    Canvas(
        modifier = Modifier
            .padding(4.dp)
            .size(28.dp)
    ) {
        drawCircle(
            color = Color.LightGray, radius = 8.dp.toPx()
        )
    }
}

@Composable
fun PasswordRoundGreen() {
    Canvas(
        modifier = Modifier
            .padding(4.dp)
            .size(28.dp)
    ) {
        drawCircle(
            color = MainGreen, radius = 8.dp.toPx()
        )
    }
}

@Composable
fun PasswordRoundRed() {
    Canvas(
        modifier = Modifier
            .padding(4.dp)
            .size(28.dp)
    ) {
        drawCircle(
            color = Color.Red, radius = 8.dp.toPx()
        )
    }
}