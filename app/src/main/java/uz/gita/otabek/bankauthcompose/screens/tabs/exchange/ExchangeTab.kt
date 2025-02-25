package uz.gita.otabek.bankauthcompose.screens.tabs.exchange

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import java.nio.file.WatchEvent

object ExchangeTab : Tab {
    private fun readResolve(): Any = ExchangeTab

    override val options: TabOptions
        @Composable
        get() {
            val title = "Обмен"

            return remember {
                TabOptions(
                    index = 0u,
                    title = title
                )
            }
        }

    @Composable
    override fun Content() {
        ExchangeTabContent()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ExchangeTabContent() {

}