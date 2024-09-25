package uz.gita.otabek.bankauthcompose.screens.tabs.services

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object ServicesTab : Tab {
    private fun readResolve(): Any = ServicesTab

    override val options: TabOptions
        @Composable
        get() {
            val title = "Сервисы"

            return remember {
                TabOptions(
                    index = 0u,
                    title = title
                )
            }
        }

    @Composable
    override fun Content() {
        Text(text = "Services")
    }
}