package uz.gita.otabek.bankauthcompose.screens.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
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
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.Grey
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.bankauthcompose.utils.AppTextField
import uz.gita.otabek.bankauthcompose.utils.Date
import uz.gita.otabek.bankauthcompose.utils.MaskVisualTransformation
import uz.gita.otabek.bankauthcompose.utils.Phone
import uz.gita.otabek.bankauthcompose.utils.isLetter
import uz.gita.otabek.presenter.signUp.SignUpContract
import uz.gita.otabek.presenter.signUp.SignUpViewModel

object SignUpScreen : Screen {
    private fun readResolve(): Any = SignUpScreen

    @Composable
    override fun Content() {
        val viewModel: SignUpContract.ViewModel = getViewModel<SignUpViewModel>()
        val uiState = viewModel.collectAsState()
        SignUpScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
fun SignUpScreenContent(
    uiState: State<SignUpContract.UiState>,
    onEventDispatcher: (SignUpContract.Intent) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(PasswordBackGroundGray)
    ) {
        val guidLine1 = createGuidelineFromTop(0.1f)
        val guidLine8 = createGuidelineFromTop(0.8f)
        val guidLine9 = createGuidelineFromTop(0.9f)
        val phone = remember { mutableStateOf("") }
        val name = remember { mutableStateOf("") }
        val surname = remember { mutableStateOf("") }
        val birthDate = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("7777") }
        val gender = remember { mutableStateOf("0") }
        Image(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .clickable {
                    onEventDispatcher(SignUpContract.Intent.MoveToBack)
                },
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .constrainAs(createRef()) {
                top.linkTo(guidLine1)
            }) {

            Text(
                text = stringResource(id = R.string.sign_up_screen_registration),
                style = TextStyle(
                    color = Color.Black, fontSize = 36.sp, fontFamily = FontFamily(Font(R.font.droid_sans_bold)), letterSpacing = 1.sp
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.sign_up_screen_phone_number),
                style = TextStyle(color = Grey, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            val focusRequester1 = FocusRequester()
            val focusRequester2 = FocusRequester()
            val focusManager = LocalFocusManager.current

            LaunchedEffect(Unit) {
                focusRequester1.requestFocus()
            }

            AppTextField(
                modifier = Modifier.focusRequester(focusRequester1),
                errorText = if (phone.value.isNotEmpty() && phone.value.length != 9) {
                    stringResource(id = R.string.sign_up_screen_phone_number_error)
                } else null,
                leadingIcon = {
                    Text(text = Phone.PREFIX.value, fontSize = 24.sp)
                    Image(painter = painterResource(id = R.drawable.divider), contentDescription = null)
                },
                value = phone.value,
                onValueChange = {
                    if (it.isDigitsOnly()) phone.value = it
                    if (it.length == 9) {
                        focusManager.clearFocus()
                        focusRequester2.requestFocus()
                    }
                },
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                visualTransformation = MaskVisualTransformation(Phone.MASK.value),
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.sign_up_screen_name),
                style = TextStyle(color = Grey, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            AppTextField(
                errorText = if (name.value.isEmpty() || name.value.length in 3..20) {
                    null
                } else stringResource(id = R.string.sign_up_screen_name_error),
                value = name.value,
                onValueChange = {
                    if (it.isLetter() && it.length < 21) {
                        name.value = it
                    }
                    if (it.length == 20) {
                        focusManager.clearFocus()
                    }
                },
                modifier = Modifier.focusRequester(focusRequester2),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.sign_up_screen_surname),
                style = TextStyle(color = Grey, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            AppTextField(
                errorText = if (surname.value.isEmpty() || surname.value.length in 3..20) {
                    null
                } else stringResource(id = R.string.sign_up_screen_surname_error),
                value = surname.value,
                onValueChange = {
                    if (it.isLetter() && it.length < 21) {
                        surname.value = it
                    }
                    if (it.length == 20) {
                        focusManager.clearFocus()
                    }
                },
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.sign_up_screen_born_date),
                style = TextStyle(color = Grey, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            AppTextField(
                errorText = if (birthDate.value.isEmpty() || birthDate.value.length == 8) {
                    null
                } else stringResource(id = R.string.sign_up_screen_born_date_error),
                value = birthDate.value,
                hint = "01.01.2024",
                onValueChange = {
                    if (it.isDigitsOnly()) birthDate.value = it
                    if (it.length == 8) focusManager.clearFocus()
                },
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                visualTransformation = MaskVisualTransformation(Date.MASK.value)
            )
        }

        Text(
            text = "Уже есть аккаунт? Войти",
            modifier = Modifier
                .constrainAs(createRef()) {
                    top.linkTo(guidLine8)
                    bottom.linkTo(guidLine8)
                }
                .clip(shape = RoundedCornerShape(10.dp))
                .clickable {
                    onEventDispatcher(SignUpContract.Intent.MoveToSignIn)
                }
        )

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
                onEventDispatcher(
                    SignUpContract.Intent.ClickNext(
                        Phone.PREFIX.value + phone.value, name.value, surname.value, birthDate.value, password.value, gender.value
                    )
                )
            }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), text = stringResource(id = R.string.sign_up_screen_continue), style = TextStyle(
                    color = Color.White, fontSize = 20.sp
                ), textAlign = TextAlign.Center
            )
        }
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(PasswordBackGroundGray)
}
