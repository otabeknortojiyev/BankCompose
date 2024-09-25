package uz.gita.otabek.bankauthcompose.screens.tabs.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.bankauthcompose.ui.theme.Purple40

object HomeTab : Tab {
    private fun readResolve(): Any = HomeTab

    override val options: TabOptions
        @Composable
        get() {
            val title = "Главная"

            return remember {
                TabOptions(
                    index = 0u,
                    title = title
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: HomeContracts.ViewModel = getViewModel<HomeViewModel>()
        HomeScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
    }
}

@Composable
private fun HomeScreenContent(
    uiState: State<HomeContracts.UiState>, onEventDispatcher: (HomeContracts.Intent) -> Unit
) {
    onEventDispatcher(HomeContracts.Intent.GetInitData)
    var isHidden by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PasswordBackGroundGray),
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 16.dp, top = 20.dp)) {
                    Image(painter = painterResource(id = R.drawable.user_circle), contentDescription = null)
                    Text(
                        text = uiState.value.name.uppercase(),
                        style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 20.sp),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.home_screen_over_all_balance),
                    style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_regular)), fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(painter = painterResource(id = if (isHidden) R.drawable.eye_hidden else R.drawable.eye_show),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable { isHidden = !isHidden }
                            .background(color = Color.Transparent)
                            .padding(10.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = if (isHidden) {
                                "* * * * * * *"
                            } else {
                                uiState.value.balance.toString()
                            },
                            style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold))),
                            fontSize = 28.sp
                        )
                        Text(
                            text = if (isHidden) "" else stringResource(id = R.string.home_screen_sum),
                            style = TextStyle(fontSize = 24.sp, color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_regular))),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.arrows_left_right),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                ) {
                    item {
                        Services(
                            R.drawable.percentage,
                            stringResource(id = R.string.home_screen_online),
                            stringResource(id = R.string.home_screen_microloan),
                            stringResource(id = R.string.home_screen_get),
                            0.dp
                        )
                    }
                    item {
                        Services(
                            R.drawable.zolotaya_korona,
                            stringResource(id = R.string.home_screen_golden),
                            stringResource(id = R.string.home_screen_crown),
                            stringResource(id = R.string.home_screen_get),
                            0.dp
                        )
                    }
                    item {
                        Services(
                            R.drawable.exchange_gray,
                            stringResource(id = R.string.home_screen_exchange2),
                            stringResource(id = R.string.home_screen_currency2),
                            stringResource(id = R.string.home_screen_exchange3),
                            0.dp
                        )
                    }
                    item {
                        Services(
                            R.drawable.credit_card,
                            stringResource(id = R.string.home_screen_debet),
                            stringResource(id = R.string.home_screen_cards),
                            stringResource(id = R.string.home_screen_order),
                            10.dp
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.home_screen_card),
                        style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold))),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(text = stringResource(id = R.string.home_screen_show_all),
                        style = TextStyle(color = MainGreen, fontFamily = FontFamily(Font(R.font.montserrat_semibold))),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable { }
                            .background(color = Color.Transparent)
                            .padding(start = 10.dp, end = 16.dp))
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White)
                ) {
                    if (uiState.value.cards.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.home_screen_has_cards),
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                                .align(alignment = Alignment.CenterHorizontally),
                            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center)
                        )
                    } else {
                        for (i in 0 until uiState.value.cards.size) {
                            if (i == uiState.value.cards.size - 1) {
                                MiniCards(
                                    type = uiState.value.cards[i].themeType,
                                    16.dp,
                                    uiState.value.cards[i].pan,
                                    uiState.value.cards[i].amount.toString(),
                                    isHidden
                                )
                            } else {
                                MiniCards(
                                    type = uiState.value.cards[i].themeType,
                                    0.dp,
                                    uiState.value.cards[i].pan,
                                    uiState.value.cards[i].amount.toString(),
                                    isHidden
                                )
                            }
                        }
                    }
                }
            }
            item {
                Text(text = stringResource(id = R.string.home_screen_monitoring),
                    style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 20.sp),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .clickable { }
                        .background(color = Color.LightGray)
                        .padding(top = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.Center)
            }
            item {
                val gradient = Brush.linearGradient(colors = listOf(MainGreen, Purple40))
                Box(modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(gradient), contentAlignment = Alignment.CenterStart) {
                    Text(
                        text = stringResource(id = R.string.home_screen_free_transfers),
                        style = TextStyle(color = Color.White, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 18.sp),
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.home_screen_exchange_in_app),
                        style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 20.sp)
                    )
                    Text(text = stringResource(id = R.string.home_screen_exchange),
                        style = TextStyle(color = MainGreen, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 16.sp),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable { }
                            .background(color = Color.Transparent)
                            .padding(vertical = 10.dp, horizontal = 4.dp))
                }
            }
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(color = Color.White), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.home_screen_currency),
                            style = TextStyle(color = Color.Gray, fontFamily = FontFamily(Font(R.font.montserrat_light)))
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            textAlign = TextAlign.Center,
                            text = "$ USD",
                            style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 20.sp)
                        )
                    }
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.home_screen_sell),
                            style = TextStyle(color = Color.Gray, fontFamily = FontFamily(Font(R.font.montserrat_light)))
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            textAlign = TextAlign.Center,
                            text = "12 670",
                            style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 20.sp)
                        )
                    }
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.home_screen_buy),
                            style = TextStyle(color = Color.Gray, fontFamily = FontFamily(Font(R.font.montserrat_light)))
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            textAlign = TextAlign.Center,
                            text = "12 750",
                            style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 20.sp)
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                ) {
                    Text(text = stringResource(id = R.string.home_screen_bramches),
                        style = TextStyle(color = Color.DarkGray, fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_semibold))),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .clickable { }
                            .background(color = Color.White)
                            .weight(1f)
                            .padding(vertical = 20.dp),
                        textAlign = TextAlign.Center)
                    Text(text = stringResource(id = R.string.home_screen_ATMs),
                        style = TextStyle(color = Color.DarkGray, fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_semibold))),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .clickable { }
                            .background(color = Color.White)
                            .weight(1f)
                            .padding(vertical = 20.dp),
                        textAlign = TextAlign.Center)
                }
            }
            item {
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun Services(image: Int, text1: String, text2: String, text3: String, end: Dp) {
    Box(modifier = Modifier
        .padding(start = 16.dp, end = end)
        .size(width = 120.dp, height = 120.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(color = Color.White)
        .clickable { }
        .padding(10.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = image), contentDescription = null, modifier = Modifier
                    .padding(start = 10.dp, top = 4.dp)
                    .weight(1.5f)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = text1,
                    style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 12.sp),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = text2,
                    style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 12.sp),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(
                text = text3,
                style = TextStyle(color = Color.Gray, fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.montserrat_light))),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun MiniCards(type: Int, bottom: Dp, text1: String, text2: String, isHidden: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = bottom)
                .size(width = 80.dp, height = 50.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(
                    if (type == 0) {
                        MainGreen
                    } else Color.White
                )
                .border(
                    width = if (type == 0) 0.dp else 1.dp, shape = RoundedCornerShape(4.dp), color = Color.LightGray
                ), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text1,
                style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold))),
                modifier = Modifier.padding(bottom = 6.dp, start = 8.dp)
            )
            Image(painter = painterResource(id = if (type == 0) R.drawable.humo2 else R.drawable.uzcard), contentDescription = null)
        }
        Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp), verticalArrangement = Arrangement.SpaceAround) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp), verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = if (isHidden) "* * * * * * *" else text2,
                    style = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), fontSize = 20.sp)
                )
                Text(
                    text = if (isHidden) "" else stringResource(id = R.string.home_screen_sum),
                    style = TextStyle(color = Color.DarkGray, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 16.sp),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = if (type == 0) "HUMO" else "UZCARD",
                style = TextStyle(color = Color.Gray, fontFamily = FontFamily(Font(R.font.montserrat_regular)), fontSize = 12.sp)
            )
        }
    }
}