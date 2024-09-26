package uz.gita.otabek.bankauthcompose.screens.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.bankauthcompose.utils.AppTextField
import uz.gita.otabek.bankauthcompose.utils.MaskVisualTransformation
import uz.gita.otabek.bankauthcompose.utils.Phone
import uz.gita.otabek.presenter.signIn.SignInContract
import uz.gita.otabek.presenter.signIn.SignInViewModel

object SignInScreen : Screen {
    private fun readResolve(): Any = SignInScreen

    @Composable
    override fun Content() {
        val viewModel: SignInContract.ViewModel = getViewModel<SignInViewModel>()
        val uiState = viewModel.collectAsState()
        SignInScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
private fun SignInScreenContent(
    uiState: State<SignInContract.UiState>, onEventDispatcher: (SignInContract.Intent) -> Unit
) {
    val phone = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester1.requestFocus()
    }
    Column(modifier = Modifier.fillMaxSize()) {
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
        AppTextField(
            modifier = Modifier.focusRequester(focusRequester2),
            value = password.value,
            onValueChange = {
                phone.value = it
            },
            textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.weight(1f))
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(MainGreen),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            onClick = {
                if (phone.value.length == 9 && password.value.isNotEmpty()) {
                    onEventDispatcher(SignInContract.Intent.MoveToVerify(phone.value, password.value))
                }
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