package uz.gita.otabek.bankauthcompose.screens.addCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
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
import uz.gita.otabek.bankauthcompose.utils.Card
import uz.gita.otabek.bankauthcompose.utils.MaskVisualTransformation
import uz.gita.otabek.bankauthcompose.utils.isLetter
import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.presenter.addCard.AddCardContract
import uz.gita.otabek.presenter.addCard.AddCardViewModel

object AddCardScreen : Screen {
    private fun readResolve(): Any = AddCardScreen

    @Composable
    override fun Content() {
        val viewModel: AddCardContract.ViewModel = getViewModel<AddCardViewModel>()
        val uiState = viewModel.collectAsState()
        AddCardScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
private fun AddCardScreenContent(
    uiState: State<AddCardContract.UiState>, onEventDispatcher: (AddCardContract.Intent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PasswordBackGroundGray)
    ) {
        if (uiState.value.success) {
            AlertDialog(onDismissRequest = {}, title = {
                Text(text = "Успешно", fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)))
            }, confirmButton = {
                Button(onClick = {
                    onEventDispatcher(AddCardContract.Intent.CloseDialog)
                }) {
                    Text(text = "OK")
                }
            })
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        onEventDispatcher(AddCardContract.Intent.MoveToHome)
                    }
                    .padding(16.dp))
            Text(
                text = stringResource(id = R.string.add_card_screen_title),
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.Center),
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
        val cardPan = remember {
            mutableStateOf("")
        }
        val cardYear = remember {
            mutableStateOf("")
        }
        val cardMonth = remember {
            mutableStateOf("")
        }
        val cardName = remember {
            mutableStateOf("")
        }
        val focusRequester1 = FocusRequester()
        val focusRequester2 = FocusRequester()
        LaunchedEffect(Unit) {
            focusRequester1.requestFocus()
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.add_card_screen_enter_card_number),
            color = Grey,
            fontFamily = FontFamily(Font(R.font.montserrat_light)),
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextField(
            value = cardPan.value,
            hint = stringResource(id = R.string.add_card_screen_card_number),
            onValueChange = {
                if (it.length < 17 && it.isDigitsOnly()) {
                    cardPan.value = it
                }
                if (it.length == 16) {
                    focusRequester2.requestFocus()
                }
            },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .focusRequester(focusRequester1),
            textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            visualTransformation = MaskVisualTransformation(Card.MASK.value)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.add_card_screen_enter_card_year),
            color = Grey,
            fontFamily = FontFamily(Font(R.font.montserrat_light)),
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextField(
            value = cardYear.value,
            hint = stringResource(id = R.string.add_card_screen_card_expire_year),
            onValueChange = {
                if (it.length < 5 && it.isDigitsOnly()) {
                    cardYear.value = it
                }
            },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .focusRequester(focusRequester2),
            textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.add_card_screen_enter_card_month),
            color = Grey,
            fontFamily = FontFamily(Font(R.font.montserrat_light)),
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextField(
            value = cardMonth.value,
            hint = stringResource(id = R.string.add_card_screen_card_expire_month),
            onValueChange = { newValue ->
                if (newValue.isEmpty()) {
                    cardMonth.value = ""
                } else if (newValue.toIntOrNull() != null && newValue.toInt() in 1..12) {
                    cardMonth.value = newValue
                }
            },
            modifier = Modifier.padding(horizontal = 20.dp),
            textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.add_card_screen_enter_card_name),
            color = Grey,
            fontFamily = FontFamily(Font(R.font.montserrat_light)),
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextField(
            value = cardName.value,
            hint = stringResource(id = R.string.add_card_screen_card_name),
            onValueChange = {
                if (it.length < 20 && it.isLetter()) {
                    cardName.value = it
                }
            },
            modifier = Modifier.padding(horizontal = 20.dp),
            textStyle = TextStyle(color = Color.DarkGray, fontSize = 24.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.weight(1f))
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(MainGreen),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            onClick = {
                if (cardPan.value.length == 16 && cardYear.value.length == 4 && cardMonth.value.isNotEmpty() && cardName.value.isNotEmpty()) {
                    onEventDispatcher(
                        AddCardContract.Intent.AddCard(
                            CardRequest.AddCard(
                                cardPan.value, cardYear.value, cardMonth.value, cardName.value
                            )
                        )
                    )
                }
            }) {
            if (uiState.value.isLoading) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    CircularLoading(
                        modifier = Modifier.padding(20.dp)
                    )
                }
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = stringResource(id = R.string.sign_up_screen_continue),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(PasswordBackGroundGray)
}

@Composable
fun CircularLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}