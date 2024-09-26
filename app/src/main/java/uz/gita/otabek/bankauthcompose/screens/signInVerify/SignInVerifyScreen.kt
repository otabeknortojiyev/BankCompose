package uz.gita.otabek.bankauthcompose.screens.signInVerify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.bankauthcompose.utils.AppTextField
import uz.gita.otabek.presenter.signInVerify.SignInVerifyContract
import uz.gita.otabek.presenter.signInVerify.SignInVerifyViewModel

class SignInVerifyScreen(private val phone: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel: SignInVerifyContract.ViewModel = getViewModel<SignInVerifyViewModel>()
        val uiState = viewModel.collectAsState()
        SignInVerifyScreenContent(uiState, viewModel::onEventDispatcher, phone)
    }
}

@Composable
private fun SignInVerifyScreenContent(
    uiState: State<SignInVerifyContract.UiState>, onEventDispatcher: (SignInVerifyContract.Intent) -> Unit, phone: String
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(PasswordBackGroundGray)
    ) {
        val guidLine1 = createGuidelineFromTop(0.1f)
        val guidLine2 = createGuidelineFromTop(0.2f)
        val guidLine3 = createGuidelineFromTop(0.3f)
        val guidLine4 = createGuidelineFromTop(0.4f)
        val guidLine45 = createGuidelineFromTop(0.45f)
        val guidLine9 = createGuidelineFromTop(0.9f)
        Image(
            painter = painterResource(id = R.drawable.arrow_left), contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .clickable {
                    onEventDispatcher(SignInVerifyContract.Intent.MoveToSignIn)
                },
        )
        Text(
            text = stringResource(id = R.string.sign_in_screen_title), modifier = Modifier
                .padding(start = 20.dp)
                .constrainAs(createRef()) {
                    top.linkTo(guidLine1)
                }, color = Color.Black, fontSize = 36.sp, fontFamily = FontFamily(Font(R.font.droid_sans_bold))
        )

        Column(modifier = Modifier.constrainAs(createRef()) {
            top.linkTo(guidLine2)
        }) {
            Text(
                text = stringResource(id = R.string.sign_up_verify_screen_phone),
                modifier = Modifier.padding(start = 20.dp),
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = phone,
                modifier = Modifier.padding(start = 20.dp),
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 20.sp
            )
        }

        val focusManager = LocalFocusManager.current
        val textValues = remember { mutableStateListOf("", "", "", "", "", "") }
        val focusRequesters = List(6) { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequesters[0].requestFocus()
        }

        Row(modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            .constrainAs(createRef()) {
                top.linkTo(guidLine3)
            }) {
            for (i in 0 until 6) {
                AppTextField(
                    value = textValues[i],
                    onValueChange = { newValue ->
                        if (textValues[0] == "") {
                            focusRequesters[0].requestFocus()
                        }
                        if (newValue.length < 2 && newValue.isDigitsOnly()) {
                            textValues[i] = newValue
                            if (i == 5 && newValue != "") focusManager.clearFocus()
                        }
                        if (newValue.isNotEmpty() && i < 5 && newValue.isDigitsOnly()) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp)
                        .focusRequester(focusRequesters[i])
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Backspace) {
                                if (textValues[i].isEmpty() && i > 0) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                            }
                            false
                        },
                    textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp, textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                )
            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .constrainAs(createRef()) {
                top.linkTo(guidLine9)
                bottom.linkTo(guidLine9)
            },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(MainGreen),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            onClick = {
                if (textValues.size == 6) {
                    onEventDispatcher(SignInVerifyContract.Intent.MoveToPassword("${textValues[0]}${textValues[1]}${textValues[2]}${textValues[3]}${textValues[4]}${textValues[5]}"))
                }
            }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = stringResource(id = R.string.sign_up_verify_screen_continue),
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        var timeLeft by remember { mutableIntStateOf(109) }

        LaunchedEffect(timeLeft) {
            if (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
        }

        Row(modifier = Modifier
            .padding(start = 20.dp, top = 10.dp)
            .constrainAs(createRef()) {
                top.linkTo(guidLine4)
            }) {
            Text(
                text = stringResource(id = R.string.sign_up_verify_screen_resend_text1),
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 16.sp
            )
            Text(
                text = "$timeLeft", color = Color.Gray, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 16.sp
            )
            Text(
                text = stringResource(id = R.string.sign_up_verify_screen_resend_text2),
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 16.sp
            )
        }

        if (timeLeft == 0) {
            Text(text = stringResource(id = R.string.sign_up_verify_screen_resend_text3),
                color = Color.Blue,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .constrainAs(createRef()) {
                        top.linkTo(guidLine45)
                    }
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable {
                        onEventDispatcher(SignInVerifyContract.Intent.ResendCode)
                        timeLeft = 109
                    }
                    .padding(10.dp))
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(PasswordBackGroundGray)
}