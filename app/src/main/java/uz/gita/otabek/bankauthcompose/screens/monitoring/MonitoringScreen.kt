package uz.gita.otabek.bankauthcompose.screens.monitoring

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.ui.theme.PasswordBackGroundGray
import uz.gita.otabek.bankauthcompose.utils.Type
import uz.gita.otabek.bankauthcompose.utils.formatNumberWithSpaces
import uz.gita.otabek.common.response.HomeResponse
import uz.gita.otabek.presenter.monitoring.MonitoringContract
import uz.gita.otabek.presenter.monitoring.MonitoringViewModel

object MonitoringScreen : Screen {
    private fun readResolve(): Any = MonitoringScreen

    @Composable
    override fun Content() {
        val viewModel: MonitoringContract.ViewModel = getViewModel<MonitoringViewModel>()
        val uiState = viewModel.collectAsState()
        MonitoringScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
private fun MonitoringScreenContent(
    uiState: State<MonitoringContract.UiState>, onEventDispatcher: (MonitoringContract.Intent) -> Unit
) {
    onEventDispatcher(MonitoringContract.Intent.DownloadLastTransfers)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PasswordBackGroundGray)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        onEventDispatcher(MonitoringContract.Intent.MoveToHome)
                    }
                    .padding(16.dp)
            )
            Text(
                text = stringResource(id = R.string.home_screen_monitoring),
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.Center),
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
        if (uiState.value.lastTransfers.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.collection_list_is_empty),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            LazyColumn {
                items(uiState.value.lastTransfers.size) {
                    Transfer(uiState.value.lastTransfers[it])
                }
            }
        }
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(PasswordBackGroundGray)
}

@Composable
fun Transfer(item: HomeResponse.TransferItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .clickable {

            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = if (item.type == Type.INCOME.value) {
                painterResource(id = R.drawable.down_income)
            } else {
                painterResource(id = R.drawable.up_outcome)
            },
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 10.dp)
                .background(color = PasswordBackGroundGray, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
        )

        Text(
            text = if (item.type == Type.INCOME.value) {
                item.from
            } else {
                item.from
            },
            fontFamily = FontFamily(Font(R.font.montserrat_light)),
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f)
        )

        val timeInMillis: Long = item.time
        val millisecondsInADay = 24 * 60 * 60 * 1000
        val remainingMillis = timeInMillis % millisecondsInADay
        val hours = (remainingMillis / (1000 * 60 * 60)).toInt()
        val minutes = ((remainingMillis / (1000 * 60)) % 60).toInt()

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 20.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = if (item.type == Type.INCOME.value) {
                    "+ " + item.amount.toString().formatNumberWithSpaces() + " ${stringResource(id = R.string.home_screen_sum)}"
                } else {
                    "- " + item.amount.toString().formatNumberWithSpaces() + " ${stringResource(id = R.string.home_screen_sum)}"
                }, color = if (item.type == Type.INCOME.value) {
                    MainGreen
                } else {
                    Color.Black
                }, fontFamily = FontFamily(Font(R.font.montserrat_regular)), textAlign = TextAlign.End
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    text = ".. " + item.to.substring(item.to.length - 4, item.to.length),
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = if (hours / 10 == 0 && minutes / 10 == 0) {
                        "0$hours:0$minutes"
                    } else if (hours / 10 != 0 && minutes / 10 == 0) {
                        "$hours:0$minutes"
                    } else if (hours / 10 == 0 && minutes / 10 != 0) {
                        "0$hours:$minutes"
                    } else {
                        "$hours:$minutes"
                    }, color = Color.Black, fontFamily = FontFamily(Font(R.font.montserrat_semibold)), textAlign = TextAlign.End
                )
            }
        }
    }
}