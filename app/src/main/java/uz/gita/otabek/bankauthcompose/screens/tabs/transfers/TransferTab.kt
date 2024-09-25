package uz.gita.otabek.bankauthcompose.screens.tabs.transfers

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object TransferTab : Tab {
    private fun readResolve(): Any = TransferTab

    override val options: TabOptions
        @Composable
        get() {
            val title = "Переводы"

            return remember {
                TabOptions(
                    index = 0u,
                    title = title
                )
            }
        }

    @Composable
    override fun Content() {
        Text(text = "Transfer")
    }
}