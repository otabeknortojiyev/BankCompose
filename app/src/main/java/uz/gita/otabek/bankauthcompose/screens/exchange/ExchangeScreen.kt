package uz.gita.otabek.bankauthcompose.screens.exchange

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object ExchangeScreen : Screen {
    private fun readResolve(): Any = ExchangeScreen

    @Composable
    override fun Content() {
        ExchangeScreenContent()
    }

}

@Composable
private fun ExchangeScreenContent() {

}