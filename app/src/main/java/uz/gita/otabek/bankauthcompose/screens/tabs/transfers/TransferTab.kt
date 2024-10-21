package uz.gita.otabek.bankauthcompose.screens.tabs.transfers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.bankauthcompose.utils.CardType
import uz.gita.otabek.common.RecentTransfersData
import uz.gita.otabek.presenter.TransfersTypeData
import uz.gita.otabek.presenter.tabs.transfers.TransferContracts
import uz.gita.otabek.presenter.tabs.transfers.TransferViewModel

object TransferTab : Tab {
    private fun readResolve(): Any = TransferTab
    override val options: TabOptions
        @Composable get() {
            val title = "Переводы"
            return remember {
                TabOptions(
                    index = 0u, title = title
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: TransferContracts.ViewModel = getViewModel<TransferViewModel>()
        val uiState = viewModel.collectAsState()
        TransferScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
fun TransferScreenContent(uiState: State<TransferContracts.UiState>, onEventDispatcher: (TransferContracts.Intent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PasswordBackGroundGray)
    ) {
        Text(
            text = stringResource(id = R.string.transfers_screen_title),
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        LazyRow(modifier = Modifier.padding(start = 16.dp, top = 20.dp), contentPadding = PaddingValues(10.dp)) {
            if (uiState.value.resentTransfers.isNotEmpty()) {
                items(uiState.value.resentTransfers) {
                    RecentTransfers(it)
                }
            }
        }
        val list: List<TransfersTypeData> = arrayListOf(
            TransfersTypeData(R.drawable.recent_card, R.string.transfers_screen_by_phone_number, TransferContracts.Intent.MoveToByPhoneNumber),
            TransfersTypeData(R.drawable.transfer_green, R.string.transfers_screen_between_my_cards, TransferContracts.Intent.MoveToBetweenMyCards),
            TransfersTypeData(R.drawable.zolotaya_korona, R.string.transfers_screen_golden_crown, TransferContracts.Intent.MoveToGoldenCrown),
            TransfersTypeData(R.drawable.visa, R.string.transfers_screen_visa_direct, TransferContracts.Intent.MoveToVISADirect)
        )
        for (data in list) {
            TransfersType(data, onEventDispatcher)
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(PasswordBackGroundGray)
    systemUiController.setNavigationBarColor(PasswordBackGroundGray)
}

@Composable
fun RecentTransfers(data: RecentTransfersData) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
    ) {
        Image(
            painter = if (data.type == CardType.UZCARD.value) {
                painterResource(id = R.drawable.uzcard)
            } else {
                painterResource(id = R.drawable.humo2)
            }, contentDescription = null, modifier = Modifier.padding(10.dp)
        )
        Text(
            text = data.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        Text(
            text = ".." + data.pan,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        Text(
            text = stringResource(id = R.string.transfers_screen_recent_transfers_transfer),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            color = Color.Gray,
            fontFamily = FontFamily(Font(R.font.montserrat_light))
        )
    }
}

@Composable
fun TransfersType(data: TransfersTypeData, onEventDispatcher: (TransferContracts.Intent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .clickable {
                onEventDispatcher(data.intent)
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = data.image),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 10.dp)
                .size(36.dp)
        )
        Text(
            text = stringResource(id = data.title), color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = null,
            modifier = Modifier.padding(end = 20.dp, top = 20.dp, bottom = 20.dp)
        )
    }
}