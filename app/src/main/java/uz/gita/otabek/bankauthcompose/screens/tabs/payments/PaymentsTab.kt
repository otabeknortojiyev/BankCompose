package uz.gita.otabek.bankauthcompose.screens.tabs.payments

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object PaymentsTab : Tab {
    private fun readResolve(): Any = PaymentsTab

    override val options: TabOptions
        @Composable
        get() {
            val title = "Оплата"

            return remember {
                TabOptions(
                    index = 0u,
                    title = title
                )
            }
        }

    @Composable
    override fun Content() {
        Text(text = "Payments")
    }
}