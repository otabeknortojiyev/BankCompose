package uz.gita.otabek.bankauthcompose.screens.transfersByPhoneNumber

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.bankauthcompose.utils.CardType
import uz.gita.otabek.bankauthcompose.utils.formatNumberWithSpaces
import uz.gita.otabek.presenter.transfersByPhoneNumber.TransfersByPhoneNumberContract
import uz.gita.otabek.presenter.transfersByPhoneNumber.TransfersByPhoneNumberViewModel


object TransfersByPhoneNumberScreen : Screen {
    private fun readResolve(): Any = TransfersByPhoneNumberScreen

    @Composable
    override fun Content() {
        val viewModel: TransfersByPhoneNumberContract.ViewModel = getViewModel<TransfersByPhoneNumberViewModel>()
        val uiState = viewModel.collectAsState()
        TransfersByPhoneNumberScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ResourceAsColor")
@Composable
private fun TransfersByPhoneNumberScreenContent(
    uiState: State<TransfersByPhoneNumberContract.UiState>, onEventDispatcher: (TransfersByPhoneNumberContract.Intent) -> Unit,
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    LaunchedEffect(openBottomSheet) {
        if (openBottomSheet) {
            coroutineScope.launch {
                bottomSheetState.show()
            }
        } else {
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PasswordBackGroundGray)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        onEventDispatcher(TransfersByPhoneNumberContract.Intent.MoveToBack)
                    }
                    .padding(16.dp))
            Text(
                text = stringResource(id = R.string.transfer_by_phone_number_screen_title),
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.Center),
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 10.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .clickable {
                openBottomSheet = true
            }) {
            Text(
                text = stringResource(id = R.string.transfer_by_phone_number_screen_from),
                color = MainGreen,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 10.dp),
                fontSize = 24.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                FromToCard(pan = uiState.value.senderPan, type = uiState.value.type.toString())
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = uiState.value.balance.toString().formatNumberWithSpaces(),
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = Color.Black,
                            fontSize = 24.sp
                        )
                        Text(
                            text = stringResource(id = R.string.home_screen_sum),
                            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Text(
                        text = if (uiState.value.type == CardType.HUMO.value) {
                            "HUMO"
                        } else {
                            "UZCARD"
                        },
                        color = Color.Gray,
                        fontFamily = FontFamily(Font(R.font.montserrat_light)),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.down), contentDescription = null, modifier = Modifier.padding(end = 16.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(MainGreen),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            onClick = {}) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = stringResource(id = R.string.transfers_screen_recent_transfers_transfer),
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
        ) {
            /*Button(onClick = {
                coroutineScope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                    if (!bottomSheetState.isVisible) {
                        openBottomSheet = false
                    }
                }
            }) {
                Text("Hide bottom sheet")
            }*/
            Text(
                text = stringResource(id = R.string.transfer_by_phone_number_screen_chose_card),
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 16.dp)
            )
            if (uiState.value.cards.isNotEmpty()) {
                LazyColumn {
                    items(uiState.value.cards) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(10.dp)
                        ) {
                            FromToCard(
                                pan = it.pan.substring(it.pan.length - 4, it.pan.length), type = if (it.themeType % 2 == 0) {
                                    CardType.UZCARD.value
                                } else {
                                    CardType.HUMO.value
                                }
                            )
                            Column(modifier = Modifier.padding(start = 10.dp)) {
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text(
                                        text = uiState.value.balance.toString().formatNumberWithSpaces(),
                                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                                        color = Color.Black,
                                        fontSize = 24.sp
                                    )
                                    Text(
                                        text = stringResource(id = R.string.home_screen_sum),
                                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 4.dp)
                                    )
                                }
                                Text(
                                    text = if (uiState.value.type == CardType.HUMO.value) {
                                        "HUMO"
                                    } else {
                                        "UZCARD"
                                    },
                                    color = Color.Gray,
                                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(PasswordBackGroundGray)
    systemUiController.setNavigationBarColor(PasswordBackGroundGray)
}

@Composable
fun FromToCard(pan: String, type: String) {
    Column(
        modifier = Modifier.background(color = MainGreen, shape = RoundedCornerShape(4.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.hamkor_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 10.dp, top = 4.dp)
                .size(16.dp)
        )
        Row(
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp, end = 4.dp, top = 4.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
//                text = pan.substring(pan.length - 4, pan.length),
                text = "6441", color = Color.Black, fontFamily = FontFamily(
                    Font(R.font.montserrat_semibold)
                )
            )
            Image(
                painter = if (type == CardType.UZCARD.value) {
                    painterResource(id = R.drawable.uzcard)
                } else {
                    painterResource(id = R.drawable.humo2)
                }, contentDescription = null, modifier = Modifier
                    .padding(start = 10.dp)
                    .size(24.dp)
            )
        }
    }
}