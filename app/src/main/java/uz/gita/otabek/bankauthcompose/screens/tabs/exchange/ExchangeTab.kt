package uz.gita.otabek.bankauthcompose.screens.tabs.exchange

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

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
        Text(text = "Exchange")
    }
}