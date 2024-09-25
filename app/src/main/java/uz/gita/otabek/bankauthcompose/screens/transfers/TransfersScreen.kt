package uz.gita.otabek.bankauthcompose.screens.transfers

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object TransfersScreen : Screen {
    private fun readResolve(): Any = TransfersScreen

    @Composable
    override fun Content() {
        TransfersScreenContent()
    }

}


@Composable
private fun TransfersScreenContent() {

}