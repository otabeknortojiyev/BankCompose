package uz.gita.otabek.bankauthcompose.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.screens.tabs.exchange.ExchangeTab
import uz.gita.otabek.bankauthcompose.screens.tabs.home.HomeTab
import uz.gita.otabek.bankauthcompose.screens.tabs.payments.PaymentsTab
import uz.gita.otabek.bankauthcompose.screens.tabs.services.ServicesTab
import uz.gita.otabek.bankauthcompose.screens.tabs.transfers.TransferTab
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen

object MainScreen : Screen {
    private fun readResolve(): Any = MainScreen

    @Composable
    override fun Content() {
        TabNavigator(tab = HomeTab) {
            Scaffold(content = {
                CurrentTab();
                val p = it
            }, bottomBar = {
                BottomAppBar(
                    actions = {
                        TabNavigationItem(tab = HomeTab)
                        TabNavigationItem(tab = PaymentsTab)
                        TabNavigationItem(tab = TransferTab)
                        TabNavigationItem(tab = ExchangeTab)
                        TabNavigationItem(tab = ServicesTab)
                    },
                    modifier = Modifier
                        .background(Color.White)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .wrapContentHeight(),
                    containerColor = Color.White
                )
            })
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    if (tab == tabNavigator.current) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(shape = RoundedCornerShape(20.dp))
                .padding(4.dp)
                .clickable { tabNavigator.current = tab },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = when (tab) {
                    HomeTab -> {
                        painterResource(id = R.drawable.home_green)
                    }

                    PaymentsTab -> {
                        painterResource(id = R.drawable.payments_green)
                    }

                    TransferTab -> {
                        painterResource(id = R.drawable.transfer_green)
                    }

                    ExchangeTab -> {
                        painterResource(id = R.drawable.exchange_green)
                    }

                    else -> {
                        painterResource(id = R.drawable.services_green)
                    }
                },
                contentDescription = tab.options.title,
            )
            Text(
                text = tab.options.title,
                style = TextStyle(color = MainGreen, fontFamily = FontFamily(Font(R.font.montserrat_light)), fontSize = 12.sp)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { tabNavigator.current = tab },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = when (tab) {
                    HomeTab -> {
                        painterResource(id = R.drawable.home_gray)
                    }

                    PaymentsTab -> {
                        painterResource(id = R.drawable.payments_gray)
                    }

                    TransferTab -> {
                        painterResource(id = R.drawable.transfer_gray)
                    }

                    ExchangeTab -> {
                        painterResource(id = R.drawable.exchange_gray)
                    }

                    else -> {
                        painterResource(id = R.drawable.services_gray)
                    }
                },
                contentDescription = tab.options.title,
            )
            Text(
                text = tab.options.title,
                style = TextStyle(color = Color.LightGray, fontFamily = FontFamily(Font(R.font.montserrat_light)), fontSize = 12.sp)
            )
        }
    }
}