package uz.gita.otabek.bankauthcompose.screens.payments

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object PaymentsScreen : Screen {
    private fun readResolve(): Any = PaymentsScreen

    @Composable
    override fun Content() {
        PaymentsScreenContent()
    }

}

@Composable
private fun PaymentsScreenContent() {

}