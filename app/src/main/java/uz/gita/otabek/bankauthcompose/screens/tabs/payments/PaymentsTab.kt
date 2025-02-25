package uz.gita.otabek.bankauthcompose.screens.tabs.payments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.presenter.tabs.payments.PaymentsContract
import uz.gita.otabek.presenter.tabs.payments.PaymentsViewModel

object PaymentsTab : Tab {
    private fun readResolve(): Any = PaymentsTab

    override val options: TabOptions
        @Composable get() {
            val title = "Оплата"

            return remember {
                TabOptions(
                    index = 0u, title = title
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: PaymentsContract.ViewModel = getViewModel<PaymentsViewModel>()
        val uiState = viewModel.collectAsState()
        PaymentsTabContent(uiState, viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PaymentsTabContent(uiState: State<PaymentsContract.UiState>, onEventDispatcher: (PaymentsContract.Intent) -> Unit) {
    val pullRefreshState = rememberPullRefreshState(refreshing = uiState.value.isLoading, onRefresh = {})
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PasswordBackGroundGray)
            .pullRefresh(pullRefreshState)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Oплата услуг",
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Payments(R.drawable.signal, R.string.payments_mobile)
            Payments(R.drawable.electricity, R.string.payments_utility)
            Payments(R.drawable.wifi, R.string.payments_internet)
            Payments(R.drawable.tv, R.string.payments_tv)
            Payments(R.drawable.government, R.string.payments_government)
            Payments(R.drawable.phone, R.string.payments_phone)
        }
        PullRefreshIndicator(
            refreshing = uiState.value.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(alignment = Alignment.TopCenter),
            backgroundColor = if (uiState.value.isLoading) Color.Red else Color.Green
        )
    }
}

@Composable
private fun Payments(image: Int, text: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .clickable {

            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 10.dp)
        )
        Text(text = stringResource(text), color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_regular)))
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = null,
            modifier = Modifier.padding(end = 20.dp, top = 20.dp, bottom = 20.dp)
        )
    }
}